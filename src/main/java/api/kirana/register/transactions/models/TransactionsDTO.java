package api.kirana.register.transactions.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@NotNull
@Data
public class TransactionsDTO {
    private String type;
    private String status;
    private String currency;
    private double amount;
    private String paymentMethod;
    private Date date;
    private String customerId;
}
