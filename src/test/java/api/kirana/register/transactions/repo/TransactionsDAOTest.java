package api.kirana.register.transactions.repo;

import api.kirana.register.transactions.entity.Transactions;
import java.text.SimpleDateFormat;
import java.util.*;
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

@ExtendWith(MockitoExtension.class)
public class TransactionsDAOTest {
    @Mock private TransactionsRepo transactionRepo;

    @InjectMocks private TransactionsDAO transactionDAO;

    @Test
    public void getAllTransactionsShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Transactions> transactions =
                Arrays.asList(
                        new Transactions(),
                        new Transactions(),
                        new Transactions(),
                        new Transactions());
        Page<Transactions> transactionsList = new PageImpl<>(transactions);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(transactionRepo.findAll(pageable)).thenReturn(transactionsList);
        Page<Transactions> actualList = transactionDAO.getAllTransactions(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionsList, actualList);
    }

    @Test
    public void getTransactionByIdShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setId("testId");
        Optional<Transactions> transactionObj = Optional.of(transaction);
        Mockito.when(transactionRepo.findById(transaction.getId())).thenReturn(transactionObj);
        Optional<Transactions> actualTransaction =
                transactionDAO.getTransactionById(transaction.getId());
        Assertions.assertNotNull(actualTransaction);
        Assertions.assertEquals(transactionObj, actualTransaction);
    }

    @Test
    public void getTransactionsByTypeShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setType("Credit");
        List<Transactions> transactionList = new ArrayList<>();
        transactionList.add(new Transactions());
        Mockito.when(transactionRepo.findByType(transaction.getType())).thenReturn(transactionList);
        List<Transactions> actualList = transactionDAO.getTransactionsByType(transaction.getType());
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList, actualList);
    }

    @Test
    public void getTransactionsByStatusShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setStatus("success");
        List<Transactions> transactionList = new ArrayList<>();
        transactionList.add(new Transactions());
        Mockito.when(transactionRepo.findByStatus(transaction.getStatus()))
                .thenReturn(transactionList);
        List<Transactions> actualList =
                transactionDAO.getTransactionByStatus(transaction.getStatus());
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList, actualList);
    }

    @Test
    public void getTransactionByDateBetweenShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = simpleDateFormat.parse("2024-07-10");
            Date endDate = simpleDateFormat.parse("2024-07-12");
            transaction.setDate(startDate);
            List<Transactions> transactionList = new ArrayList<>();
            transactionList.add(new Transactions());
            Mockito.when(transactionRepo.findByDateBetween(startDate, endDate))
                    .thenReturn(transactionList);
            List<Transactions> actualList =
                    transactionDAO.getTransactionByDateBetween(startDate, endDate);
            Assertions.assertNotNull(actualList);
            Assertions.assertEquals(transactionList, actualList);
        } catch (Exception e) {
            Assertions.fail("Date parsing failed: " + e.getMessage());
        }
    }

    @Test
    public void saveTransactionShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setAmount(100.0);
        transaction.setType("Credit");
        transaction.setStatus("Completed");
        transaction.setCustomerId("12345");
        transaction.setPaymentMethod("Card");
        Mockito.when(transactionRepo.save(transaction)).thenReturn(transaction);
        Transactions actualResponse = transactionDAO.saveTransaction(transaction);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(transaction, actualResponse);
    }

    @Test
    public void deleteTransactionShouldReturnSuccess() {
        String transactionId = "12345";
        Mockito.doNothing().when(transactionRepo).deleteById(transactionId);
        // Call the deleteTransaction method on transactionDAO
        transactionDAO.deleteTransaction(transactionId);
        Mockito.verify(transactionRepo, Mockito.times(1)).deleteById(transactionId);
    }
}
