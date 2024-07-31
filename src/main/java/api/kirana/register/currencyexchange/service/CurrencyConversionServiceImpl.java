package api.kirana.register.currencyexchange.service;

import api.kirana.register.currencyexchange.models.ConversionResponse;
import api.kirana.register.transactions.entity.Transactions;
import api.kirana.register.transactions.repo.TransactionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService{

    @Autowired
    private final TransactionsDAO transactionDAO;

    @Autowired
    public CurrencyConversionServiceImpl(TransactionsDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * Retrieves the transaction amount by transaction ID.
     *
     * @param id the ID of the transaction
     * @return the transaction amount
     * @throws IllegalArgumentException if the transaction data is not found for the given ID
     */
    @Override
    public double getAmountById(String id) {
        Optional<Transactions> optionalCurrencyData = transactionDAO.getTransactionById(id);
        if (optionalCurrencyData.isPresent()) {
            return Double.parseDouble(String.valueOf(optionalCurrencyData.get().getAmount()));
        } else {
            throw new IllegalArgumentException("Currency data not found for ID: " + id);
        }
    }

    /**
     * Converts the currency amount of a transaction from a base currency to a target currency.
     *
     * @param id the ID of the transaction
     * @param baseCurrency the base currency code
     * @param targetCurrency the target currency code
     * @return a string representing the conversion result
     */
    @Override
    public String convertCurrency(String id,String baseCurrency, String targetCurrency) {
        double baseAmount = getAmountById(id);
        String API_URL = "https://api.fxratesapi.com/latest";
        String url = API_URL + "?base=" + baseCurrency;
        // Make a REST call to the external API
        ConversionResponse response = restTemplate.getForObject(url, ConversionResponse.class);
        // Extract the exchange rate for the target currency from the response
        assert response != null;
        double exchangeRate = response.getRates().get(targetCurrency);
        // Calculate the converted amount
        double convertedAmount = baseAmount * exchangeRate;
        // Construct and return the response string
        return " "+baseCurrency+"-"+baseAmount+","+targetCurrency+"-"+convertedAmount;
    }
}