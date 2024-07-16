package api.kirana.register.security;

import api.kirana.register.user.models.Users;
import api.kirana.register.user.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    /**
     * Loads the user by username.
     *
     * @param username the username identifying the user whose data is required
     * @return a fully populated UserDetails object (never null)
     * @throws UsernameNotFoundException if the user could not be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userInfo = repository.findByUserName(username);
        UserDetails userDetails = userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

        if(userDetails == null) {
            System.out.println("Null User");
        }
        else {
            System.out.println("User found " + userDetails.getUsername());
            System.out.println("Password found " + userDetails.getPassword());
            System.out.println("Auth found " + userDetails.getAuthorities().toString());

        }

        return userDetails;
    }
}

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return getUserDetails();
//    }
//
//    private static UserDetails getUserDetails() {
//        return null;
//    }
