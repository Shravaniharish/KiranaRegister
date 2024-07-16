package api.kirana.register.Users.repo;

import api.kirana.register.user.models.Users;
import api.kirana.register.user.repo.UserDAO;
import api.kirana.register.user.repo.UserRepository;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserDAOTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDAO userDAO;


    @Test
    public void getAllUsersShouldReturnSuccess(){
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());
        Mockito.when(userRepository.findAll()).thenReturn(usersList);
        List<Users> actualList = userDAO.getAllUsers();
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
    public void saveUser(){
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
