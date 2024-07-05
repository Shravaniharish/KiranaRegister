package api.kirana.register.user.repo;

import api.kirana.register.user.models.Users;
import api.kirana.register.user.models.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<Users> getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Users saveUser(Users userRequest) {
        return userRepository.save(userRequest);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
