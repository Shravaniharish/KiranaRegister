package api.kirana.register.currencyexchange.controller;

import api.kirana.register.currencyexchange.controllers.CurrencyConversionController;
import api.kirana.register.currencyexchange.service.CurrencyConversionService;
import api.kirana.register.response.ApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionControllerTest {
    @Mock
    private CurrencyConversionService conversionService;

    @InjectMocks
    private CurrencyConversionController conversionController;

    @Test
    public void convertCurrencyReturnsSuccess(){
        String id = "test";
        String baseCurrency = "INR";
        String targetCurrency = "USD";
        String expectedRate = "100.00";
        Mockito.when(conversionService.convertCurrency(id, baseCurrency, targetCurrency)).thenReturn(expectedRate);
        ResponseEntity<ApiResponse>  actualResponse = conversionController.convertCurrency(id, baseCurrency, targetCurrency);
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedRate, actualResponse.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }



}
