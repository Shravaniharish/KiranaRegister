package api.kirana.register.transaction.service;

import api.kirana.register.transaction.models.Transaction;
import api.kirana.register.transaction.models.TransactionDTO;
import api.kirana.register.transaction.repo.TransactionDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    private TransactionDAO transactionDAO;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    public void getAllTransactionsShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("test-id");

        List<Transaction> transactions = List.of(transaction);

        Mockito.when(transactionDAO.getAllTransactions()).thenReturn(transactions);
        List<Transaction> actualTransactions =  transactionService.getAllTransactions();

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);
    }

    @Test
    public void getAllTransactionsShouldHandleFailure() {
        Mockito.when(transactionDAO.getAllTransactions()).thenThrow(new RuntimeException());
        List<Transaction> actualTransactions =  transactionService.getAllTransactions();

        Assertions.assertNull(actualTransactions);
    }

    @Test
    public void getTransactionByIdShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("test-id");

        Optional<Transaction> transactions = Optional.of(transaction);

        Mockito.when(transactionDAO.getTransactionById(transaction.getTransactionId())).thenReturn(transactions);
        Optional<Transaction> actualTransactions = transactionService.getTransactionById(transaction.getTransactionId());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);


    }

    @Test
    public void getTransactionsByTypeShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("CREDIT");

        List<Transaction> transactions = List.of(transaction);

        Mockito.when(transactionDAO.getTransactionsByType(transaction.getTransactionType())).thenReturn(transactions);
        List<Transaction> actualTransactions = transactionService.getTransactionsByType(transaction.getTransactionType());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);
    }

    @Test
    public void getTransactionByStatusShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus("Success");

        List<Transaction> transactions = List.of(transaction);

        Mockito.when(transactionDAO.getTransactionByStatus(transaction.getTransactionStatus())).thenReturn(transactions);
        List<Transaction> actualTransactions = transactionService.getTransactionByStatus(transaction.getTransactionStatus());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactions, actualTransactions);

    }

    @Test
    public void getTransactionByDateShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate("2024-07-03");

        List<Transaction> transactionList = List.of(transaction);

        Mockito.when(transactionDAO.getTransactionByDate(transaction.getTransactionDate())).thenReturn(transactionList);
        List<Transaction> actualTransactions = transactionService.getTransactionByDate(transaction.getTransactionDate());

        Assertions.assertNotNull(actualTransactions);
        Assertions.assertEquals(transactionList, actualTransactions);
    }

    @Test
    public void saveTransactionShouldReturnSuccess() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionAmount(100.0);
        transactionDTO.setTransactionType("Credit");
        transactionDTO.setTransactionStatus("Completed");
        transactionDTO.setCustomerId("12345");
        transactionDTO.setPaymentMethod("Card");

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(100.0);
        transaction.setTransactionType("Credit");
        transaction.setTransactionStatus("Completed");
        transaction.setCustomerId("12345");
        transaction.setPaymentMethod("Card");
        Mockito.when(transactionDAO.saveTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);

        Transaction actualTransaction = transactionService.saveTransaction(transactionDTO);

        Assertions.assertNotNull(actualTransaction);
        Assertions.assertEquals(transaction.getTransactionAmount(), actualTransaction.getTransactionAmount());
        Assertions.assertEquals(transaction.getTransactionType(), actualTransaction.getTransactionType());
        Assertions.assertEquals(transaction.getTransactionStatus(), actualTransaction.getTransactionStatus());
        Assertions.assertEquals(transaction.getCustomerId(), actualTransaction.getCustomerId());
        Assertions.assertEquals(transaction.getPaymentMethod(), actualTransaction.getPaymentMethod());
    }

    @Test
    public void deleteTransactionShouldReturnSuccess() {
        String transactionId = "12345";
        String expectedResponse = "Transaction deleted";
        Mockito.doNothing().when(transactionDAO).deleteTransaction(transactionId);
        String actualResponse = transactionService.deleteTransaction(transactionId);
        Mockito.verify(transactionDAO, Mockito.times(1)).deleteTransaction(transactionId);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }


}