package api.kirana.register.security;

import api.kirana.register.users.entity.Users;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsersInfo implements UserDetails {

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    /**
     * Constructs a new UserInfoUserDetails instance with user information.
     *
     * @param userInfo the user information
     */
    public UsersInfo(Users userInfo) {
        this.name = userInfo.getUserName();
        this.password = userInfo.getPassword();
        this.authorities =
                Arrays.stream(userInfo.getRole().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        // bcrypt and send
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
