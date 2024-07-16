package api.kirana.register.currencyexchange.service;

public interface CurrencyConversionService {
    double getAmountById(String id);

    String convertCurrency(String id,String baseCurrency, String targetCurrency);


}
