package api.kirana.register.transaction.controller;

import api.kirana.register.response.Response;
import api.kirana.register.transaction.controllers.TransactionControllers;
import api.kirana.register.transaction.models.Transaction;
import api.kirana.register.transaction.models.TransactionDTO;
import api.kirana.register.transaction.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionControllers transactionController;

    @Test
    public void getAllTransactionsShouldReturnSuccess() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Response expectedResponse = new Response();
        expectedResponse.setData(transactionList);
        expectedResponse.setMessage("List of all transactions");

        Mockito.when(transactionService.getAllTransactions()).thenReturn(transactionList);

        ResponseEntity<Response> responseEntity = transactionController.getAllTransactions();

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getMessage(), responseEntity.getBody().getMessage());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void getTransactionByIdShouldReturnSuccess() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionId("test-id");
        Optional<Transaction> transactionOptional = Optional.of(transaction);
        Response expectedResponse = new Response();
        expectedResponse.setData(transactionOptional);
        expectedResponse.setMessage("transaction id match");

        Mockito.when(transactionService.getTransactionById(transaction.getTransactionId())).thenReturn(transactionOptional);

        ResponseEntity<Response> responseEntity = transactionController.getTransactionById(transaction.getTransactionId());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void getTransactionByStatusShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus("success");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Response expectedResponse = new Response();
        expectedResponse.setData(transactionList);
        expectedResponse.setMessage("transaction status match");

        Mockito.when(transactionService.getTransactionByStatus(transaction.getTransactionStatus())).thenReturn(transactionList);

        ResponseEntity<Response> responseEntity = transactionController.getTransactionByStatus(transaction.getTransactionStatus());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getMessage(), responseEntity.getBody().getMessage());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());

    }

    @Test
    public void getTransactionByDateShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate("2024-07-03");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Response expectedResponse = new Response();
        expectedResponse.setData(transactionList);

        Mockito.when(transactionService.getTransactionByDate(transaction.getTransactionDate())).thenReturn(transactionList);
        ResponseEntity<Response> responseEntity = transactionController.getTransactionByDate(transaction.getTransactionDate());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());

    }

    @Test
    public void getTransactionByDateBetweenShouldReturnSuccess() {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate("2024-07-03");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        Response expectedResponse = new Response();
        expectedResponse.setData(transactionList);
        Mockito.when(transactionService.getTransactionByDateBetween(transaction.getTransactionDate(), transaction.getTransactionDate())).thenReturn(transactionList);
        ResponseEntity<Response> responseEntity = transactionController.getTransactionByDateBetween(transaction.getTransactionDate(), transaction.getTransactionDate());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void saveTransactions() {
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


        Response expectedResponse = new Response();
        expectedResponse.setData(transaction);

        Mockito.when(transactionService.saveTransaction(transactionDTO)).thenReturn(transaction);
        ResponseEntity<Response> response = transactionController.saveTransactions(transactionDTO);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(expectedResponse.getData(), response.getBody().getData());

    }

    @Test
    public void deleteTransactionsShouldReturnSuccess() {
        String transactionId = "12345";
        String transactionResponse = "Transaction deleted";
        Mockito.when(transactionService.deleteTransaction(transactionId)).thenReturn(transactionResponse);
        ResponseEntity<Response> responseEntity = transactionController.deleteTransaction(transactionId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
    }
}