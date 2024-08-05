package api.kirana.register.users.repo;

import api.kirana.register.users.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersDAO {

    private final UsersRepository userRepository;

    @Autowired
    public UsersDAO(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of Users
     */
    public Page<Users> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
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
