package api.kirana.register.users.controller;

import api.kirana.register.response.ApiResponse;
import api.kirana.register.users.models.UsersDTO;
import api.kirana.register.users.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing the response with all users
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        ApiResponse response = new ApiResponse();
        Pageable pageable = PageRequest.of(page, size);
        response.setData(userService.getAllUsers(pageable));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the response with the user information
     */
    @GetMapping()
    public ResponseEntity<ApiResponse> getUser(
            @Valid @RequestParam(required = false) String id,
            @RequestParam(required = false) String name) {
        ApiResponse response = new ApiResponse();
        if (id == null && name == null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (id != null) {
            response.setData(userService.getUserById(id));
        } else if (name != null) {
            response.setData(userService.getUserByName(name));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Saves a new user.
     *
     * @param userRequest the request body containing user information to save
     * @return a ResponseEntity containing the response with the saved user information
     */
    @PostMapping()
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UsersDTO userRequest) {
        ApiResponse response = new ApiResponse();
        response.setData(userService.saveUser(userRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity containing the response indicating the deletion status
     */
    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteUser(@Valid @RequestParam String id) {
        ApiResponse response = new ApiResponse();
        response.setData(userService.deleteUser(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
