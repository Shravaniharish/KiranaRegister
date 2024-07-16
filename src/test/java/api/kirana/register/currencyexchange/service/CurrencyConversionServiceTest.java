package api.kirana.register.currencyexchange.service;

import api.kirana.register.transaction.models.Transaction;
import api.kirana.register.transaction.repo.TransactionDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionServiceTest {
    @Mock
    private TransactionDAO transactionDAO;

    @InjectMocks
    private CurrencyConversionServiceImpl currencyConversionService;

    @Test
    public void getAmountByIdShouldReturnSuccess() {
        String id ="test";
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(100.0);
        Optional<Transaction> expectedTransaction = Optional.of(transaction);
        Mockito.when(transactionDAO.getTransactionById(id)).thenReturn(expectedTransaction);
        double amount = currencyConversionService.getAmountById(id);
        Assertions.assertEquals(100.0, amount);

    }



}
