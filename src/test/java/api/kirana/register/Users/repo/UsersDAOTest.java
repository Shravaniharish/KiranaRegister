package api.kirana.register.Users.repo;

import api.kirana.register.users.entity.Users;
import api.kirana.register.users.repo.UsersDAO;
import api.kirana.register.users.repo.UsersRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class UsersDAOTest {

    @Mock private UsersRepository userRepository;

    @InjectMocks private UsersDAO userDAO;

    @Test
    public void getAllUsersShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Users> users = Arrays.asList(new Users(), new Users(), new Users(), new Users());
        Page<Users> usersList = new PageImpl<>(users);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(userRepository.findAll(pageable)).thenReturn(usersList);
        Page<Users> actualList = userDAO.getAllUsers(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(usersList, actualList);
    }

    @Test
    public void getUserByIdShouldReturnSuccess() {
        Users user = new Users();
        user.setUserId("user-id");
        Optional<Users> expectedOutcome = Optional.of(user);
        Mockito.when(userRepository.findById(user.getUserId())).thenReturn(expectedOutcome);
        Optional<Users> actualOutcome = userDAO.getUserById(user.getUserId());
        Assertions.assertNotNull(actualOutcome);
        Assertions.assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    public void getUserByNameShouldReturnSuccess() {
        Users user = new Users();
        user.setUserName("abc");
        Optional<Users> expectedOutcome = Optional.of(user);
        Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(expectedOutcome);
        Optional<Users> actualOutcome = userDAO.getUserByName(user.getUserName());
        Assertions.assertNotNull(actualOutcome);
        Assertions.assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    public void saveUser() {
        Users user = new Users();
        user.setUserId("test-id");
        user.setUserContactNumber("9876543210");
        user.setRole("Admin");
        user.setUserName("abc");
        user.setEmail("abc@xyz.com");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Users actualOutcome = userDAO.saveUser(user);
        Assertions.assertNotNull(actualOutcome);
        Assertions.assertEquals(user, actualOutcome);
    }

    @Test
    public void deleteUser() {
        String userId = "12345";
        Mockito.doNothing().when(userRepository).deleteById(userId);
        userDAO.deleteUser(userId);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }
}
