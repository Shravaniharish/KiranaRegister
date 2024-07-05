package api.kirana.register.user.models;

import lombok.Data;

import java.util.Date;
@Data
public class UsersDTO {
    private String userName;
    private String email;
    private String password;
    private Date registrationDate;
    private String role;
    private String userContactNumber;
}
