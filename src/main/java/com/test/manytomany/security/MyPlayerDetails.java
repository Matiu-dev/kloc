package com.test.manytomany.security;

import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyPlayerDetails implements UserDetails {

    private Player player;

    public MyPlayerDetails(Player player){
        this.player = player;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(player.getPlayerRole().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return player.getPassword();
    }

    @Override
    public String getUsername() {
        return player.getLogin();
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

        if(player.getPlayerStatus().equals(PlayerStatus.DISABLED)){
            System.out.println("Konto zamknięte");
            return false;
        } else {
            return true;
        }
    }

    public Player getUser() {
        return player;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [worker=" + player + "]";
    }
}


