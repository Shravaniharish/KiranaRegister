package api.kirana.register.transaction.models;

import lombok.Data;
@Data
public class TransactionDTO {
    private String transactionType;
    private String transactionStatus;
    private double transactionAmount;
    private String paymentMethod;
    private String transactionTime;
    private String transactionDate;
    private String customerId;
}
