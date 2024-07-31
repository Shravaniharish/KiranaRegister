package api.kirana.register.transactions.helpers;

import api.kirana.register.transactions.entity.Transactions;
import api.kirana.register.transactions.models.AggregationResponse;
import api.kirana.register.transactions.repo.TransactionsDAO;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ReportingHelper {
    private TransactionsDAO transactionsDAO;

    public ReportingHelper(TransactionsDAO transactionsDAO) {
        this.transactionsDAO = transactionsDAO;
    }
    public void getWeeklyAggregations(LocalDate start, LocalDate end, Map<String, AggregationResponse> aggregations) {
        LocalDate startOfWeek = start.with(DayOfWeek.MONDAY);
        if (start.isBefore(startOfWeek)) {
            startOfWeek = startOfWeek.minusWeeks(1);
        }

        LocalDate endOfWeek = end.with(DayOfWeek.SUNDAY);
        if (end.isAfter(endOfWeek)) {
            endOfWeek = endOfWeek.plusWeeks(1);
        }

        int weekNumber = 1;
        LocalDate currentStart = startOfWeek;

        while (!currentStart.isAfter(endOfWeek)) {
            LocalDate weekEndDate = currentStart.with(DayOfWeek.SUNDAY);
            if (weekEndDate.isAfter(end)) {
                weekEndDate = end;
            }

            Date startDate = Date.from(currentStart.atStartOfDay(ZoneId.of("UTC")).toInstant());
            Date endDate = Date.from(weekEndDate.atStartOfDay(ZoneId.of("UTC")).toInstant());

            AggregationResponse response = calculateAggregation(startDate, endDate);
            String weekKey =  currentStart.toString() + " : " + weekEndDate.toString() ;
            aggregations.put(weekKey, response);

            currentStart = currentStart.plusWeeks(1);
            weekNumber++;
        }
    }

    public void getMonthlyAggregations(LocalDate start, LocalDate end, Map<String, AggregationResponse> aggregations) {
        LocalDate currentStart = start.withDayOfMonth(1);
        LocalDate endOfMonth = end.withDayOfMonth(end.lengthOfMonth());
        if (endOfMonth.isAfter(end)) {
            endOfMonth = end;
        }

        while (currentStart.isBefore(end) || currentStart.isEqual(end)) {
            LocalDate currentEnd = currentStart.withDayOfMonth(currentStart.lengthOfMonth());
            if (currentEnd.isAfter(endOfMonth)) {
                currentEnd = endOfMonth;
            }

            Date startDate = Date.from(currentStart.atStartOfDay(ZoneId.of("UTC")).toInstant());
            Date endDate = Date.from(currentEnd.atStartOfDay(ZoneId.of("UTC")).toInstant());

            AggregationResponse response = calculateAggregation(startDate, endDate);
            String monthKey = currentStart.getMonth().toString() + " " + currentStart.getYear();
            aggregations.put(monthKey, response);

            currentStart = currentStart.plusMonths(1).withDayOfMonth(1);
        }
    }

    public void getYearlyAggregations(LocalDate start, LocalDate end, Map<String, AggregationResponse> aggregations) {
        LocalDate currentStart = start.withDayOfYear(1);
        LocalDate endOfYear = end.withDayOfYear(end.lengthOfYear());
        if (endOfYear.isAfter(end)) {
            endOfYear = end;
        }

        while (currentStart.isBefore(end) || currentStart.isEqual(end)) {
            LocalDate currentEnd = currentStart.withDayOfYear(currentStart.lengthOfYear());
            if (currentEnd.isAfter(endOfYear)) {
                currentEnd = endOfYear;
            }

            Date startDate = Date.from(currentStart.atStartOfDay(ZoneId.of("UTC")).toInstant());
            Date endDate = Date.from(currentEnd.atStartOfDay(ZoneId.of("UTC")).toInstant());

            AggregationResponse response = calculateAggregation(startDate, endDate);
            String yearKey = String.valueOf(currentStart.getYear());
            aggregations.put(yearKey, response);

            currentStart = currentStart.plusYears(1).withDayOfYear(1);
        }
    }

    private AggregationResponse calculateAggregation(Date start, Date end) {
        List<Transactions> transactions = transactionsDAO.getTransactionByDateBetween(start, end);
        double totalCredit = 0.0;
        double totalDebit = 0.0;
        for (Transactions transaction : transactions) {
            if ("credit".equalsIgnoreCase(transaction.getType())) {
                totalCredit += transaction.getAmount();
            } else if ("debit".equalsIgnoreCase(transaction.getType())) {
                totalDebit += transaction.getAmount();
            }
        }
        double netFlow = totalCredit - totalDebit;

        AggregationResponse response = new AggregationResponse();
        response.setNetFlow(netFlow);
        response.setCredit(totalCredit);
        response.setDebit(totalDebit);

        return response;
    }

}