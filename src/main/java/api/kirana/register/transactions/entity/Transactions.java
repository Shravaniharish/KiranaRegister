package api.kirana.register.transactions.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;


@Data
@Document(collection="Transaction")
@EqualsAndHashCode(callSuper = true)
public class Transactions extends DateAudit implements Serializable {
    @Id
    @NotEmpty
    private String id;
    @Indexed
    private String type;
    @Indexed
    private String status;
    private String currency;
    private double amount;
    private String paymentMethod;
//    private String transactionTime;
//    private String transactionDate;
    private Date date;
    private String customerId;
}
