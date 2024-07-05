package api.kirana.register.user.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "Users")
public class Users {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String password;
    private Date registrationDate;
    private String role;
    private String userContactNumber;



}
