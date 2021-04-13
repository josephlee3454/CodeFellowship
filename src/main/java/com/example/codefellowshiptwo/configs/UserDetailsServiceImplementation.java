package com.example.codefellowshiptwo.configs;



//
//import com.codefellows.dinosaurs.DinosaursApplication;
//import com.codefellows.dinosaurs.models.dinosaurUser.DinosaurUserRepository;
import com.example.codefellowshiptwo.Models.user.CodeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    CodeUserRepository codeUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return codeUserRepository.findByUsername(username);
    }
}
