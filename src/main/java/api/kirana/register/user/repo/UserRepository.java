package api.kirana.register.user.repo;

import api.kirana.register.user.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users,String> {

    //to obtain user details using userName
    Optional<Users> findByUserName(String userName);
}
