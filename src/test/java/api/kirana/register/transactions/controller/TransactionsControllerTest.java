package api.kirana.register.transactions.controller;

import api.kirana.register.response.ApiResponse;
import api.kirana.register.transactions.controllers.TransactionsControllers;
import api.kirana.register.transactions.entity.Transactions;
import api.kirana.register.transactions.models.TransactionsDTO;
import api.kirana.register.transactions.service.TransactionsService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransactionsControllerTest {
    @Mock
    private TransactionsService transactionService;

    @InjectMocks
    private TransactionsControllers transactionController;

    @Test
    public void getAllTransactionsShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Transactions> transactions = Arrays.asList(new Transactions(), new Transactions(), new Transactions(), new Transactions());
        Page<Transactions> transactionsList = new PageImpl<>(transactions);
        Pageable pageable = PageRequest.of(page, size);
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(transactionsList);

        Mockito.when(transactionService.getAllTransactions(pageable)).thenReturn(transactionsList);

        ResponseEntity<ApiResponse> responseEntity = transactionController.getAllTransactions(page, size);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void getTransactionByIdShouldReturnSuccess() {
        // Arrange
        Transactions transaction = new Transactions();
        transaction.setId("test-id");
        Optional<Transactions> transactionOptional = Optional.of(transaction);
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(transactionOptional);

        Mockito.when(transactionService.getTransactionById(transaction.getId())).thenReturn(transactionOptional);

        ResponseEntity<ApiResponse> responseEntity = transactionController.getTransactionById(transaction.getId());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void getTransactionByStatusShouldReturnSuccess() {
        Transactions transaction = new Transactions();
        transaction.setStatus("success");
        List<Transactions> transactionList = new ArrayList<>();
        transactionList.add(new Transactions());
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(transactionList);

        Mockito.when(transactionService.getTransactionByStatus(transaction.getStatus())).thenReturn(transactionList);

        ResponseEntity<ApiResponse> responseEntity = transactionController.getTransactionByStatus(transaction.getStatus());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());

    }

    @Test
    public void getTransactionByDateShouldReturnSuccess() {
        String date = "2024-07-03";
        Transactions transaction = new Transactions();
        transaction.setDate(Date.valueOf(date));
        List<Transactions> transactionList = List.of(transaction);
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(transactionList);

        Mockito.when(transactionService.getTransactionByDate(date)).thenReturn(transactionList);

        ResponseEntity<ApiResponse> responseEntity = transactionController.getTransactionByDate(date);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void getTransactionByDateBetweenShouldReturnSuccess() {
        String startDate = "2024-07-01";
        String endDate = "2024-07-03";
        Transactions transaction = new Transactions();
        transaction.setDate(Date.valueOf(startDate));
        List<Transactions> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(transactionList);

        Mockito.when(transactionService.getTransactionByDateBetween(startDate, endDate)).thenReturn(transactionList);

        ResponseEntity<ApiResponse> responseEntity = transactionController.getTransactionByDateBetween(startDate, endDate);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }

    @Test
    public void saveTransactionsShouldReturnSuccess() {
        TransactionsDTO transactionDTO = new TransactionsDTO();
        transactionDTO.setAmount(100.0);
        transactionDTO.setType("Credit");
        transactionDTO.setStatus("Completed");
        transactionDTO.setCustomerId("12345");
        transactionDTO.setPaymentMethod("Card");

        Transactions transaction = new Transactions();
        transaction.setAmount(100.0);
        transaction.setType("Credit");
        transaction.setStatus("Completed");
        transaction.setCustomerId("12345");
        transaction.setPaymentMethod("Card");

        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(transaction);

        Mockito.when(transactionService.saveTransaction(transactionDTO)).thenReturn(transaction);

        ResponseEntity<ApiResponse> responseEntity = transactionController.saveTransactions(transactionDTO);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(expectedResponse.getData(), responseEntity.getBody().getData());
    }



    @Test
    public void deleteTransactionsShouldReturnSuccess() {
        String transactionId = "12345";
        String transactionResponse = "Transaction deleted";
        Mockito.when(transactionService.deleteTransaction(transactionId)).thenReturn(transactionResponse);
        ResponseEntity<ApiResponse> responseEntity = transactionController.deleteTransaction(transactionId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
    }
}