package com.example.Lab5.services;

import com.example.Lab5.daos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.example.Lab5.entities.MyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = UserRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("MyUser not found: " + username));
        return new User(user.getEmail(), user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(),user.isCredentialsNonExpired(), user.isAccountNonLocked(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}


//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public MyUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyUser user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("MyUser not found with username: " + username);
//        }
//
//        return org.springframework.security.core.userdetails.MyUser
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole()) // Assign roles from the MyUser entity
//                .build();
//    }
//}
