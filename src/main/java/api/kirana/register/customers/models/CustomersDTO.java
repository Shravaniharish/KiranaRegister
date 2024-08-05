package api.kirana.register.customers.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@NotNull
@Data
public class CustomersDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String storeName;
}
