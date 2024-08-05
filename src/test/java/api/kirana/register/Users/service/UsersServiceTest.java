package api.kirana.register.Users.service;

import api.kirana.register.users.entity.Users;
import api.kirana.register.users.models.UsersDTO;
import api.kirana.register.users.repo.UsersDAO;
import api.kirana.register.users.service.UsersServiceImpl;
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
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {
    @Mock private UsersDAO userDAO;

    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks private UsersServiceImpl userService;

    @Test
    public void getAllUsersShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Users> users = Arrays.asList(new Users(), new Users(), new Users(), new Users());
        Page<Users> usersList = new PageImpl<>(users);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(userDAO.getAllUsers(pageable)).thenReturn(usersList);
        Page<Users> actualList = userService.getAllUsers(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(usersList, actualList);
    }

    @Test
    public void getUserByIdShouldReturnSuccess() {
        Users user = new Users();
        user.setUserId("test-id");
        Optional<Users> expectedObject = Optional.of(user);
        Mockito.when(userDAO.getUserById(user.getUserId())).thenReturn(expectedObject);
        Optional<Users> actualObj = userService.getUserById(user.getUserId());
        Assertions.assertNotNull(actualObj);
        Assertions.assertEquals(expectedObject, actualObj);
    }

    @Test
    public void getUserByNameShouldReturnSuccess() {
        Users user = new Users();
        user.setUserName("abc");
        Optional<Users> expectedObject = Optional.of(user);
        Mockito.when(userDAO.getUserByName(user.getUserName())).thenReturn(expectedObject);
        Optional<Users> actualObj = userService.getUserByName(user.getUserName());
        Assertions.assertNotNull(actualObj);
        Assertions.assertEquals(expectedObject, actualObj);
    }

    @Test
    public void saveUserShouldReturnSuccess() {
        Users user = new Users();
        user.setUserContactNumber("9876543210");
        user.setRole("Admin");
        user.setUserName("abc");
        user.setEmail("abc@xyz.com");
        user.setPassword("encodedPassword");

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserContactNumber("9876543210");
        usersDTO.setRole("Admin");
        usersDTO.setUserName("abc");
        usersDTO.setEmail("abc@xyz.com");
        usersDTO.setPassword("rawPassword");

        // Mock password encoding
        Mockito.when(passwordEncoder.encode(usersDTO.getPassword())).thenReturn("encodedPassword");

        Mockito.when(userDAO.saveUser(Mockito.any(Users.class))).thenReturn(user);

        Users actualUser = userService.saveUser(usersDTO);

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(user.getUserName(), actualUser.getUserName());
        Assertions.assertEquals(user.getUserContactNumber(), actualUser.getUserContactNumber());
        Assertions.assertEquals(user.getRole(), actualUser.getRole());
        Assertions.assertEquals(user.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(user.getPassword(), actualUser.getPassword());
    }

    @Test
    public void deleteUserShouldReturnSuccess() {
        String userId = "test-id";
        // setup mock to call userDAO.deleteUser(userID) and do nothing
        Mockito.doNothing().when(userDAO).deleteUser(userId);
        String response = userService.deleteUser(userId);
        // setup mock to ensure userDAO.deleteUser(userID) is called only once
        Mockito.verify(userDAO, Mockito.times(1)).deleteUser(userId);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("test-id", response);
    }
}
