package api.kirana.register.customers.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Customer")
public class Customers {
    @Id @NotEmpty private String id;
    @Indexed private String name;
    private String email;
    private String phoneNumber;
    private String storeName;
}
