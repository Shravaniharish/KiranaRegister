package api.kirana.register.user.service;
import api.kirana.register.user.models.Users;
import api.kirana.register.user.models.UsersDTO;
import api.kirana.register.user.repo.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAOImpl, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAOImpl;

        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of Users
     */
    @Override
    public List<Users> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the User if found, or empty if not found
     */
    @Override
    public Optional<Users> getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param userName the username of the user to retrieve
     * @return an Optional containing the User if found, or empty if not found
     */
    @Override
    public Optional<Users> getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    /**
     * Saves a new user.
     *
     * @param userRequest the request body containing user information to save
     * @return the saved User entity
     */
    @Override
    public Users saveUser(UsersDTO userRequest) {
        Users user = new Users();
        user.setUserName(userRequest.getUserName());
        user.setUserContactNumber(userRequest.getUserContactNumber());
        user.setEmail(userRequest.getUserContactNumber());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getPassword());
        user.setRegistrationDate(new Date());
        return userDAO.saveUser(user);

    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     * @return a message indicating the deletion status
     */
    @Override
    public String deleteUser(String userId) {
         userDAO.deleteUser(userId);
         return "user is deleted";
    }
}
