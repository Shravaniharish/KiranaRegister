package api.kirana.register.security;

import api.kirana.register.users.entity.Users;
import api.kirana.register.users.repo.UsersRepository;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsersInfoService implements UserDetailsService {

    private static final Log log = LogFactory.getLog(UsersInfoService.class);
    @Autowired private UsersRepository repository;

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
        UserDetails userDetails =
                userInfo.map(UsersInfo::new)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("user not found " + username));

        if (userDetails == null) {
            log.info("user not found " + username);
        }
        log.info("user found " + userDetails.getUsername());
        log.info("Auth found " + userDetails.getAuthorities().toString());
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
