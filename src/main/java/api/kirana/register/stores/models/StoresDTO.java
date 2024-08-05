package api.kirana.register.stores.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@NotNull
@Data
public class StoresDTO {

    private String name;
    private String address;
    private String phoneNumber;
}
