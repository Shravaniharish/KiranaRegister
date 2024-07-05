package api.kirana.register.user.service;
import api.kirana.register.user.models.Users;
import api.kirana.register.user.models.UsersDTO;
import api.kirana.register.user.repo.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAOImpl;


    @Autowired
    public UserServiceImpl(UserDAO userDAOImpl) {
        this.userDAOImpl = userDAOImpl;

    }

    @Override
    public List<Users> getAllUsers() {
        return userDAOImpl.getAllUsers();
    }

    @Override
    public Optional<Users> getUserById(String userId) {
        return userDAOImpl.getUserById(userId);
    }

    @Override
    public Optional<Users> getUserByName(String userName) {
        return userDAOImpl.getUserByName(userName);
    }

    @Override
    public Users saveUser(UsersDTO userRequest) {
        Users user = new Users();
        user.setUserName(userRequest.getUserName());
        user.setUserContactNumber(userRequest.getUserContactNumber());
        user.setEmail(userRequest.getUserContactNumber());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getPassword());
        user.setRegistrationDate(new Date());
        return userDAOImpl.saveUser(user);

    }

    @Override
    public String deleteUser(String userId) {
         userDAOImpl.deleteUser(userId);
         return "user is deleted";
    }
}
