package api.kirana.register.user.service;

import api.kirana.register.user.models.Users;
import api.kirana.register.user.models.UsersDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<Users> getAllUsers();

    public Optional<Users> getUserById(String userId);

    public Optional<Users> getUserByName(String userName);

    public Users saveUser(UsersDTO user);

    public String deleteUser(String userId);

}
