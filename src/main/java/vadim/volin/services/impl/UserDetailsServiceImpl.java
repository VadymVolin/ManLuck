package vadim.volin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String usermail) throws UsernameNotFoundException {
        User user = userService.getByUserMail(usermail);

        if (user == null) {
            throw new UsernameNotFoundException("User mail: " + usermail);
        }
        List<GrantedAuthority> roles =
                Arrays.stream(user.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsermail(), user.getPassword(), roles);
        return userDetails;
    }
}
