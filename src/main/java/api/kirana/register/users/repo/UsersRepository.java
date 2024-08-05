package api.kirana.register.users.repo;

import api.kirana.register.users.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, String> {

    Page<Users> findAll(Pageable pageable);

    // to obtain user details using userName
    Optional<Users> findByUserName(String userName);
}
