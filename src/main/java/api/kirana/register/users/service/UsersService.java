package api.kirana.register.users.service;

import api.kirana.register.users.entity.Users;
import api.kirana.register.users.models.UsersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public Page<Users> getAllUsers(Pageable pageable);

    public Optional<Users> getUserById(String userId);

    public Optional<Users> getUserByName(String userName);

    public Users saveUser(UsersDTO user);

    public String deleteUser(String userId);

}
