package api.kirana.register.transactions.service;

import api.kirana.register.transactions.entity.Transactions;
import api.kirana.register.transactions.helpers.CurrencyConversionHelper;
import api.kirana.register.transactions.helpers.ReportingHelper;
import api.kirana.register.transactions.models.AggregationResponse;
import api.kirana.register.transactions.models.TransactionsDTO;
import api.kirana.register.transactions.repo.TransactionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsDAO transactionDAO;
    private final CurrencyConversionHelper currencyConversionHelper;
    private final ReportingHelper reportingHelper;

    @Autowired
    public TransactionsServiceImpl(TransactionsDAO transactionDAO, CurrencyConversionHelper currencyConversionHelper, ReportingHelper reportingHelper) {
        this.transactionDAO = transactionDAO;
        this.currencyConversionHelper = currencyConversionHelper;
        this.reportingHelper = reportingHelper;
    }

    /**
     * lists all transactions along with their specific details
     * @return
     */
    //@Cacheable(value = "transactions", key = "#pageable")
    @Override
    public Page<Transactions> getAllTransactions(Pageable pageable){
        return transactionDAO.getAllTransactions(pageable);
    }
    /**
     * displays details of transaction on specifying transaction details
     * @param id
     * @return
     */
   // @Cacheable(value = "transactions", key = "#id")
    @Override
    public Optional<Transactions> getTransactionById(String id) {
        return transactionDAO.getTransactionById(id);
    }

    /**
     * displays list of transactions for a specified transaction type
     * @param type
     * @return
     */
    //@Cacheable(value = "transactions", key = "#type")
    @Override
    public List<Transactions> getTransactionsByType(String type) {
        return transactionDAO.getTransactionsByType(type);
    }

    /**
     * displays list of transactions for a specified transaction status
     * @param status
     * @return
     */
   // @Cacheable(value = "transactions", key = "#status")
    @Override
    public List<Transactions> getTransactionByStatus(String status) {
        return transactionDAO.getTransactionByStatus(status);
    }

    /**
     * displays list of transactions for a specified transaction date
     * @param date
     * @return
     */
    //@Cacheable(value = "transactions", key = "#date")
    @Override
    public List<Transactions> getTransactionByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedLocalDate = LocalDate.parse(date, formatter);
        Date start = Date.from(parsedLocalDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date end = calendar.getTime();
        return transactionDAO.getTransactionByDateBetween(start, end);
    }

    /**
     * displays all transaction that were carried out between the startDate and endDate
     * @param startDate
     * @param endDate
     */
    //@Cacheable(value = "transactions", key = "{#startDate, #endDate}")
    @Override
    public List<Transactions> getTransactionByDateBetween(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedStartDate = LocalDate.parse(startDate, formatter);
        LocalDate parsedEndDate = LocalDate.parse(endDate, formatter);
        Date start = Date.from(parsedStartDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
        Date end = Date.from(parsedEndDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
        return transactionDAO.getTransactionByDateBetween(start, end);
    }

        /**
         * saves transaction into the database
         * @param transactionRequest
         */

   // @CachePut(value = "transactions", key = "#transactionRequest.id")
    @Override
    public Transactions saveTransaction(TransactionsDTO transactionRequest) {
        Transactions transaction = new Transactions();
        transaction.setCurrency(transactionRequest.getCurrency());
        transaction.setAmount(currencyConversionHelper.convertAmount(transactionRequest.getCurrency(),"INR",transactionRequest.getAmount()));
        transaction.setType(transactionRequest.getType());
        transaction.setStatus(transactionRequest.getStatus());
        transaction.setCustomerId(transactionRequest.getCustomerId());
        transaction.setPaymentMethod(transactionRequest.getPaymentMethod());
        transaction.setDate(transactionRequest.getDate());
        //transaction.setTransactionTime(String.valueOf(LocalTime.parse(LocalTime.now().toString())));
        //transaction.setDate(String.valueOf(LocalDate.parse(LocalDate.now().toString())));
        return transactionDAO.saveTransaction(transaction);
    }

    /**
     * deletes transaction for the specified transaction id
     * @param id
     */
    //@CacheEvict(value = "transactions", key = "#id")
    @Override
    public String deleteTransaction(String id) {
        transactionDAO.deleteTransaction(id);
        return id;
    }

    @Override
    public Map<String, AggregationResponse> getReports(String reportType, String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedStartDate = LocalDate.parse(startDateStr, formatter);
        LocalDate parsedEndDate = LocalDate.parse(endDateStr, formatter);

        Map<String, AggregationResponse> aggregations = new LinkedHashMap<>();

        switch (reportType.toLowerCase()) {
            case "weekly":
                reportingHelper.getWeeklyAggregations(parsedStartDate, parsedEndDate, aggregations);
                break;
            case "monthly":
                reportingHelper.getMonthlyAggregations(parsedStartDate, parsedEndDate, aggregations);
                break;
            case "yearly":
                reportingHelper.getYearlyAggregations(parsedStartDate, parsedEndDate, aggregations);
                break;
            default:
                throw new IllegalArgumentException("Invalid report type: " + reportType);
        }

        return aggregations;
    }


}
