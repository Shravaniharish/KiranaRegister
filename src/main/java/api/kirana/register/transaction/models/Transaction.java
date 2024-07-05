package api.kirana.register.transaction.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document(collection="Transaction")
public class Transaction implements Serializable {
    @Id
    private String transactionId;
    private String transactionType;
    private String transactionStatus;
    private double transactionAmount;
    private String paymentMethod;
    private String transactionTime;
    private String transactionDate;
    private String customerId;
}
