package com.test.manytomany.security;

import com.test.manytomany.model.Player;
import com.test.manytomany.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class MyPlayerDetailsService implements UserDetailsService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Player> users = playerRepository.findByLoginTwo(s);
        users.orElseThrow(() -> new UsernameNotFoundException("Not found: " + s));

        return users.map(MyPlayerDetails::new).get();
    }
}