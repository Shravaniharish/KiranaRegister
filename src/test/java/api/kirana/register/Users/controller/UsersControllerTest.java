package api.kirana.register.Users.controller;

import api.kirana.register.response.ApiResponse;
import api.kirana.register.users.controller.UsersController;
import api.kirana.register.users.entity.Users;
import api.kirana.register.users.models.UsersDTO;
import api.kirana.register.users.service.UsersService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {
    private static final Log log = LogFactory.getLog(UsersControllerTest.class);
    @Mock private UsersService userService;

    @InjectMocks private UsersController userController;

    @Test
    public void getAllUsersShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Users> users = Arrays.asList(new Users(), new Users(), new Users(), new Users());
        Page<Users> usersList = new PageImpl<>(users);
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(usersList);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(userService.getAllUsers(pageable)).thenReturn(usersList);
        ResponseEntity<ApiResponse> actualResponse = userController.getAllUsers(page, size);
        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(expectedResponse.getData(), actualResponse.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getUserShouldReturnSuccess() {
        Users user = new Users();
        user.setUserId("test");
        user.setUserName("abc");
        Optional<Users> expectedUser = Optional.of(user);
        Mockito.when(userService.getUserById(user.getUserId())).thenReturn(expectedUser);
        ResponseEntity<ApiResponse> actualResponse = userController.getUser(user.getUserId(), null);
        Assertions.assertNotNull(actualResponse.getBody());
        Assertions.assertEquals(expectedUser, actualResponse.getBody().getData());
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
        ApiResponse expectedResponse = new ApiResponse();
        expectedResponse.setData(user);

        Mockito.when(userService.saveUser(usersDTO)).thenReturn(user);
        ResponseEntity<ApiResponse> response = userController.saveUser(usersDTO);
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
        ResponseEntity<ApiResponse> responseEntity = userController.deleteUser(userId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
    }
}
