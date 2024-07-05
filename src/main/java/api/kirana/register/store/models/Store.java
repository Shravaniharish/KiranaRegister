package api.kirana.register.store.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Store")
public class Store {
    @Id
    private String storeId;
    private String storeName;
    private String storeAddress;
    private String storeContactNo;

}
