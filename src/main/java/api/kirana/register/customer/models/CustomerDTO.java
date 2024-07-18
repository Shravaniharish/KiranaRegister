package api.kirana.register.customer.models;

import lombok.Data;

@Data
public class CustomerDTO {
    private String customerName;
    private String email;
    private String phoneNumber;
    private String storeName;
}
