package api.kirana.register.currencyexchange.controllers;

import api.kirana.register.currencyexchange.service.CurrencyConversionService;
import api.kirana.register.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
public class CurrencyConversionController {

    private final CurrencyConversionService conversionService;

    @Autowired
    public CurrencyConversionController(CurrencyConversionService conversionService) {
        this.conversionService = conversionService;
    }


    /**
     * Converts the currency amount of a transaction from a base currency to a target currency.
     * Only accessible by users with the ADMIN authority.
     *
     * @param id the ID of the transaction
     * @param baseCurrency the base currency code
     * @param targetCurrency the target currency code
     * @return a ResponseEntity containing the conversion result and HTTP status
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/convert")
    public ResponseEntity<ApiResponse> convertCurrency(@RequestParam String id, @RequestParam String baseCurrency, @RequestParam String targetCurrency) {
        ApiResponse response = new ApiResponse();
        String data =conversionService.convertCurrency(id, baseCurrency, targetCurrency);
        response.setData(data);
        return new ResponseEntity<>(response , HttpStatus.OK);

    }


}