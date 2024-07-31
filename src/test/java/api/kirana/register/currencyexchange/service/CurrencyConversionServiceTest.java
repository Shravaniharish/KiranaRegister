package api.kirana.register.currencyexchange.service;

import api.kirana.register.transactions.repo.TransactionsDAO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionServiceTest {
    @Mock
    private TransactionsDAO transactionDAO;

    @InjectMocks
    private CurrencyConversionServiceImpl currencyConversionService;

//    @Test
//    public void getAmountByIdShouldReturnSuccess() {
//        String id ="test";
//        Transactions transaction = new Transactions();
//        transaction.setTransactionAmount(100.0);
//        Optional<Transactions> expectedTransaction = Optional.of(transaction);
//        Mockito.when(transactionDAO.getTransactionById(id)).thenReturn(expectedTransaction);
//        double amount = currencyConversionService.getAmountById(id);
//        Assertions.assertEquals(100.0, amount);
//
//    }



}
