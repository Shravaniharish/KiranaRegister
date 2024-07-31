package api.kirana.register.stores.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Store")
public class Stores {
    @Id
    @NotEmpty
    private String id;
    @Indexed
    private String name;
    private String address;
    private String phoneNumber;

}
