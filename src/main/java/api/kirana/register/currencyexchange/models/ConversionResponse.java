package api.kirana.register.currencyexchange.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionResponse {


    private boolean success;
    private String terms;
    private String privacy;
    private String date;
    private String base;
    private Map<String, Double> rates;

}
