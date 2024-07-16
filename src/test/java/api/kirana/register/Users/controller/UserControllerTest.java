package api.kirana.register.Users.controller;

import api.kirana.register.response.Response;
import api.kirana.register.user.controller.UserController;
import api.kirana.register.user.models.Users;
import api.kirana.register.user.models.UsersDTO;
import api.kirana.register.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getAllUsersShouldReturnSuccess() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users());
        Response expectedResponse = new Response();
        expectedResponse.setData(usersList);
        expectedResponse.setMessage("List of all Users");
        Mockito.when(userService.getAllUsers()).thenReturn(usersList);
        ResponseEntity<Response> actualResponse = userController.getAllUsers();
        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getUsersByIdShouldReturnSuccess() {
        Users user = new Users();
        user.setUserId("test");
        Optional<Users> expectedUser = Optional.of(user);
        Response expectedResponse = new Response();
        expectedResponse.setData(expectedUser);
        Mockito.when(userService.getUserById(user.getUserId())).thenReturn(expectedUser);
        ResponseEntity<Response> actualResponse = userController.getUsersById(user.getUserId());
        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getUsersByNameShouldReturnSuccess() {
        Users user = new Users();
        user.setUserName("abc");
        Optional<Users> expectedUser = Optional.of(user);
        Response expectedResponse = new Response();
        expectedResponse.setData(expectedUser);
        Mockito.when(userService.getUserByName(user.getUserName())).thenReturn(expectedUser);
        ResponseEntity<Response> actualResponse = userController.getUsersByName(user.getUserName());
        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
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
        Response expectedResponse = new Response();
        expectedResponse.setData(user);

        Mockito.when(userService.saveUser(usersDTO)).thenReturn(user);
        ResponseEntity<Response> response = userController.saveUser(usersDTO);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(expectedResponse.getData(), response.getBody().getData());

    }

    @Test
    public void deleteUserShouldReturnSuccess() {
        String userId = "123";
        String userResponse = "User is deleted";
        Mockito.when(userService.deleteUser(userId)).thenReturn(userResponse);
        ResponseEntity<Response> responseEntity=userController.deleteUser(userId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
    }




}
