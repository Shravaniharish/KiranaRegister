package api.kirana.register.transactions.service;

import api.kirana.register.transactions.entity.Transactions;
import api.kirana.register.transactions.enums.ReportType;
import api.kirana.register.transactions.helpers.CurrencyConversionHelper;
import api.kirana.register.transactions.helpers.ReportingHelper;
import api.kirana.register.transactions.models.AggregationResponse;
import api.kirana.register.transactions.models.TransactionsDTO;
import api.kirana.register.transactions.repo.TransactionsDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceImplTest {
    @Mock
    private TransactionsDAO transactionDAO;
    @Mock
    private CurrencyConversionHelper currencyConversionHelper;
    @Mock
    private ReportingHelper reportingHelper;

    @InjectMocks
    private TransactionsServiceImpl transactionService;

    @Test
    public void getAllTransactionsShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Transactions> transactions = Arrays.asList(new Transactions(), new Transactions(), new Transactions(), new Transactions());
        Page<Transactions> transactionsList = new PageImpl<>(transactions);
        Pageable pageable = PageRequest.of(page, size);

        Mockito.when(transactionDAO.getAllTransactions(pageable)).thenReturn(transactionsList);
        Page<Transactions> actualTransactions = transactionService.getAllTransactions(pageable);

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactionsList, actualTransactions);
    }

    @Test
    public void getTransactionByIdShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setId("test-id");

        Optional<Transactions> transactions = Optional.of(transaction);

        Mockito.when(transactionDAO.getTransactionById(transaction.getId())).thenReturn(transactions);
        Optional<Transactions> actualTransactions = transactionService.getTransactionById(transaction.getId());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);
    }

    @Test
    public void getTransactionsByTypeShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setType("CREDIT");

        List<Transactions> transactions = List.of(transaction);

        Mockito.when(transactionDAO.getTransactionsByType(transaction.getType())).thenReturn(transactions);
        List<Transactions> actualTransactions = transactionService.getTransactionsByType(transaction.getType());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);
    }

    @Test
    public void getTransactionByStatusShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setStatus("Success");

        List<Transactions> transactions = List.of(transaction);

        Mockito.when(transactionDAO.getTransactionByStatus(transaction.getStatus())).thenReturn(transactions);
        List<Transactions> actualTransactions = transactionService.getTransactionByStatus(transaction.getStatus());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);
    }

    @Test
    public void getTransactionByDateShouldReturnSuccess() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2024-07-03");
            Transactions transaction = new Transactions();
            transaction.setDate(date);

            List<Transactions> transactionList = List.of(transaction);

            Mockito.when(transactionDAO.getTransactionByDateBetween(Mockito.any(Date.class), Mockito.any(Date.class)))
                    .thenReturn(transactionList);
            List<Transactions> actualTransactions = transactionService.getTransactionByDate("2024-07-03");

            Assertions.assertNotNull(actualTransactions);
            Assertions.assertEquals(transactionList, actualTransactions);
        } catch (Exception e) {
            Assertions.fail("Date parsing failed: " + e.getMessage());
        }
    }

    @Test
    public void saveTransactionShouldReturnSuccess() {
        TransactionsDTO transactionDTO = new TransactionsDTO();
        transactionDTO.setAmount(100.0);
        transactionDTO.setType("Credit");
        transactionDTO.setStatus("success");
        transactionDTO.setCustomerId("12345");
        transactionDTO.setPaymentMethod("Card");
        transactionDTO.setCurrency("INR");
        transactionDTO.setDate(new Date());

        Transactions expectedTransaction = new Transactions();
        expectedTransaction.setAmount(100.0);
        expectedTransaction.setType("Credit");
        expectedTransaction.setStatus("success");
        expectedTransaction.setCustomerId("12345");
        expectedTransaction.setPaymentMethod("Card");
        expectedTransaction.setCurrency("INR");
        expectedTransaction.setDate(transactionDTO.getDate());

        // Mocking the currency conversion helper
        Mockito.when(currencyConversionHelper.convertAmount(transactionDTO.getCurrency(), "INR", transactionDTO.getAmount()))
                .thenReturn(100.0);
        Mockito.when(transactionDAO.saveTransaction(Mockito.any(Transactions.class))).thenAnswer(invocation -> {
            Transactions savedTransaction = invocation.getArgument(0);
            savedTransaction.setId("test-id");
            return savedTransaction;
        });
        Transactions actualTransaction = transactionService.saveTransaction(transactionDTO);
        Assertions.assertNotNull(actualTransaction);
        Assertions.assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        Assertions.assertEquals(expectedTransaction.getPaymentMethod(), actualTransaction.getPaymentMethod());
        Assertions.assertEquals(expectedTransaction.getStatus(), actualTransaction.getStatus());

    }

    @Test
    void getReportsShouldReturnMonthlyAggregations() {
        String reportType = "MONTHLY";
        String startDateStr = "2024-07-01";
        String endDateStr = "2024-07-31";

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        Map<String, AggregationResponse> expectedAggregations = new LinkedHashMap<>();
        expectedAggregations.put("JULY 2024", new AggregationResponse());

        Mockito.when(reportingHelper.getMonthlyAggregations(startDate, endDate)).thenReturn(expectedAggregations);

        Map<String, AggregationResponse> actualAggregations = transactionService.getReports(ReportType.valueOf(reportType), startDateStr, endDateStr);
        Assertions.assertNotNull(actualAggregations);
        Assertions.assertEquals(expectedAggregations.size(), actualAggregations.size());
        Assertions.assertEquals(expectedAggregations, actualAggregations);
    }

    @Test
    void getReportsShouldReturnYearlyAggregations() {
        String reportType = "YEARLY";
        String startDateStr = "2024-01-01";
        String endDateStr = "2024-12-31";

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        Map<String, AggregationResponse> expectedAggregations = new LinkedHashMap<>();
        expectedAggregations.put("2024", new AggregationResponse());

        Mockito.when(reportingHelper.getYearlyAggregations(startDate, endDate)).thenReturn(expectedAggregations);

        Map<String, AggregationResponse> actualAggregations = transactionService.getReports(ReportType.valueOf(reportType), startDateStr, endDateStr);
        Assertions.assertNotNull(actualAggregations);
        Assertions.assertEquals(expectedAggregations.size(), actualAggregations.size());
        Assertions.assertEquals(expectedAggregations, actualAggregations);
    }

    @Test
    public void deleteTransactionShouldReturnSuccess() {
        String transactionId = "12345";
        Mockito.doNothing().when(transactionDAO).deleteTransaction(transactionId);
        String actualResponse = transactionService.deleteTransaction(transactionId);
        Mockito.verify(transactionDAO, Mockito.times(1)).deleteTransaction(transactionId);
        Assertions.assertEquals(transactionId, actualResponse);
    }
}
