package ro.msg.learning.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.User;
import ro.msg.learning.shop.repository.UserRepository;
import ro.msg.learning.shop.repository.UserRoleRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {

        User user = userRepository.findByUsername(s);

        if (null == user) {
            throw new UsernameNotFoundException("No user present with username " + s);
        }

        List<String> userRoles = userRoleRepository.findRoleByUsername(s);
        return new CustomUserDetails(user, userRoles);
    }
}
