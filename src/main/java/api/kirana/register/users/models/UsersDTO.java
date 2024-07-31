package api.kirana.register.users.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@NotNull
@Data
public class UsersDTO {
    private String userName;
    private String email;
    private String password;
    private Date registrationDate;
    private String role;
    private String userContactNumber;
}
