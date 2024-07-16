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

    /**
     * Retrieves a list of all users.
     *
     * @return a list of Users
     */
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the User if found, or empty if not found
     */
    public Optional<Users> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param userName the username of the user to retrieve
     * @return an Optional containing the User if found, or empty if not found
     */
    public Optional<Users> getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Saves a user.
     *
     * @param userRequest the User entity to save
     * @return the saved User entity
     */
    public Users saveUser(Users userRequest) {
        return userRepository.save(userRequest);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
