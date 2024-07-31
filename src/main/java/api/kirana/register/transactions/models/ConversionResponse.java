package api.kirana.register.transactions.models;

import lombok.Data;

import java.util.Map;
@Data
public class ConversionResponse {
    private String base;
    private Map<String, Double> rates;
}
