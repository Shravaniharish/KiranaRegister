package api.kirana.register.user.controller;
import api.kirana.register.response.Response;
import api.kirana.register.user.models.UsersDTO;
import api.kirana.register.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllUsers() {
        Response  response = new Response();
        response.setData(userService.getAllUsers());
        response.setMessage("List of all users");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<Response> getUsersById(@RequestParam String userId) {
        Response  response = new Response();
        response.setData(userService.getUserById(userId));
        response.setMessage("user id match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUserByName")
    public ResponseEntity<Response> getUsersByName(@RequestParam String Name) {
        Response  response = new Response();
        response.setData(userService.getUserByName(Name));
        response.setMessage("user name match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<Response> saveUser(@RequestBody UsersDTO userRequest) {
        Response  response = new Response();
        response.setData(userService.saveUser(userRequest));
        response.setMessage("user is saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMapping")
    public ResponseEntity<Response> deleteUser(@RequestParam String id) {
        Response  response = new Response();
        response.setData(userService.deleteUser(id));
        response.setMessage("user is deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
