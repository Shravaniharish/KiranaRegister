package api.kirana.register.customer.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Customer")
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String storeName;



}
