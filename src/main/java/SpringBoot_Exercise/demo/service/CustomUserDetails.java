package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.SiteUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final String name;

    public CustomUserDetails(SiteUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.name = user.getName();
    }

    public String getName() {
        return name;
    }
}
