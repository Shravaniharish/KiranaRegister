package api.kirana.register.transaction.repo;

import api.kirana.register.transaction.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransactionDAOTest {
    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionDAO transactionDAO;

    @Test
    public void  getAllTransactionsShouldReturnSuccess() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Mockito.when(transactionRepo.findAll()).thenReturn(transactionList);
        List<Transaction> actualList = transactionDAO.getAllTransactions();
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList,actualList);
    }

    @Test
    public void getTransactionByIdShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("testId");
        Optional<Transaction> transactionObj = Optional.of(transaction);
        Mockito.when(transactionRepo.findById(transaction.getTransactionId())).thenReturn(transactionObj);
        Optional<Transaction> actualTransaction = transactionDAO.getTransactionById(transaction.getTransactionId());
        Assertions.assertNotNull(actualTransaction);
        Assertions.assertEquals(transactionObj,actualTransaction);

    }

    @Test
    public void getTransactionsByTypeShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("Credit");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Mockito.when(transactionRepo.findByTransactionType(transaction.getTransactionType())).thenReturn(transactionList);
        List<Transaction> actualList = transactionDAO.getTransactionsByType(transaction.getTransactionType());
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList, actualList);
    }

    @Test
    public void getTransactionsByStatusShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus("success");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Mockito.when(transactionRepo.findByTransactionStatus(transaction.getTransactionStatus())).thenReturn(transactionList);
        List<Transaction> actualList = transactionDAO.getTransactionByStatus(transaction.getTransactionStatus());
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList, actualList);
    }

    @Test
    public void getTransactionByDateShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate("2024-07-11");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Mockito.when(transactionRepo.findByTransactionDate(transaction.getTransactionDate())).thenReturn(transactionList);
        List<Transaction> actualList = transactionDAO.getTransactionByDate(transaction.getTransactionDate());
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList, actualList);

    }

    @Test
    public void getTransactionByDateBetweenShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate("2024-07-11");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Mockito.when(transactionRepo.findByTransactionDateBetween(transaction.getTransactionDate(),transaction.getTransactionDate())).thenReturn(transactionList);
        List<Transaction> actualList = transactionDAO.getTransactionByDateBetween(transaction.getTransactionDate(),transaction.getTransactionDate());
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(transactionList, actualList);
    }

    @Test
    public void saveTransactionShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(100.0);
        transaction.setTransactionType("Credit");
        transaction.setTransactionStatus("Completed");
        transaction.setCustomerId("12345");
        transaction.setPaymentMethod("Card");
        Mockito.when(transactionRepo.save(transaction)).thenReturn(transaction);
        Transaction actualResponse = transactionDAO.saveTransaction(transaction);
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
