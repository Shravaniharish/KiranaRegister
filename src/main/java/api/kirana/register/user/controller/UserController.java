package api.kirana.register.user.controller;
import api.kirana.register.response.Response;
import api.kirana.register.user.models.UsersDTO;
import api.kirana.register.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing the response with all users
     */
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllUsers() {
        Response  response = new Response();
        response.setData(userService.getAllUsers());
        response.setMessage("List of all users");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a ResponseEntity containing the response with the user information
     */
    @GetMapping("/getUserById")
    public ResponseEntity<Response> getUsersById(@RequestParam String userId) {
        Response  response = new Response();
        response.setData(userService.getUserById(userId));
        response.setMessage("user id match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their name.
     *
     * @param Name the name of the user to retrieve
     * @return a ResponseEntity containing the response with the user information
     */
    @GetMapping("/getUserByName")
    public ResponseEntity<Response> getUsersByName(@RequestParam String Name) {
        Response  response = new Response();
        response.setData(userService.getUserByName(Name));
        response.setMessage("user name match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Saves a new user.
     *
     * @param userRequest the request body containing user information to save
     * @return a ResponseEntity containing the response with the saved user information
     */
    @PostMapping("/saveUser")
    public ResponseEntity<Response> saveUser(@RequestBody UsersDTO userRequest) {
        Response  response = new Response();
        response.setData(userService.saveUser(userRequest));
        response.setMessage("user is saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity containing the response indicating the deletion status
     */
    @DeleteMapping("/deleteMapping")
    public ResponseEntity<Response> deleteUser(@RequestParam String id) {
        Response  response = new Response();
        response.setData(userService.deleteUser(id));
        response.setMessage("user is deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
