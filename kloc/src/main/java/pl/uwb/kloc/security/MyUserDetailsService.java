package pl.uwb.kloc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.uwb.kloc.model.User;
import pl.uwb.kloc.repository.UserRepository;

import java.util.Optional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> users = userRepository.findByLoginTwo(s);
        users.orElseThrow(() -> new UsernameNotFoundException("Not found: " + s));

        return users.map(MyUserDetails::new).get();
    }
}
