package api.kirana.register.users.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "Users")
public class Users {
    @NotEmpty
    @Id
    private String userId;
    @Indexed
    private String userName;
    private String email;
    private String password;
    private Date registrationDate;
    private String role;
    private String userContactNumber;



}
