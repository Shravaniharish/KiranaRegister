package api.kirana.register.transactions.helpers;

import api.kirana.register.transactions.models.ConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.time.Duration;

@Service
public class CurrencyConversionHelper {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CurrencyConversionHelper(RestTemplate restTemplate, @Qualifier("redisKVTemplate") RedisTemplate<String, String> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public double convertAmount(String baseCurrency, String targetCurrency, double amount){
        double conversionRate = getCurrencyConversionRate(baseCurrency, targetCurrency);
        return amount * conversionRate;
    }

    public double getCurrencyConversionRate(String baseCurrency, String targetCurrency) {
        String redisCurrencyConversionKey = prepareRedisKey(baseCurrency, targetCurrency);
        String cachedConversionRate = redisTemplate.opsForValue().get(redisCurrencyConversionKey);
        if (cachedConversionRate != null && !cachedConversionRate.isEmpty()) {
            return Double.parseDouble(cachedConversionRate);
        }

        String API_URL = "https://api.fxratesapi.com/latest";
        String url = API_URL + "?base=" + baseCurrency;

        // Make a REST call to the external API
        ConversionResponse response = restTemplate.getForObject(url, ConversionResponse.class);

        if (response != null && response.getRates().containsKey(targetCurrency)) {
            double conversionRate = response.getRates().get(targetCurrency);
            redisTemplate.opsForValue().set(redisCurrencyConversionKey, String.valueOf(conversionRate), Duration.ofMinutes(1));
            return conversionRate;
        } else {
            throw new IllegalArgumentException("Invalid currency or API response");
        }
    }

    private String prepareRedisKey(String sourceCurrency, String targetCurrency) {
        return MessageFormat.format("r:currency_conversion:{0}:{1}", sourceCurrency, targetCurrency);
    }
}
