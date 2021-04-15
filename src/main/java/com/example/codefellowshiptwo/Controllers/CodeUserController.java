package com.example.codefellowshiptwo.Controllers;

import com.example.codefellowshiptwo.Models.user.CodeUser;
import com.example.codefellowshiptwo.Models.user.CodeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class CodeUserController {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CodeUserRepository codeUserRepository;
//
    @Autowired
    AuthenticationManager authenticationManager;
//
//
//
    @PostMapping("/codeUser")
    public RedirectView createUser(String username, String password, HttpServletRequest request) {
        String passwordEncoded = passwordEncoder.encode(password);
        System.out.println("password = " + passwordEncoded);
        CodeUser codeUser = new CodeUser();
        codeUser.setPassword(passwordEncoded);
        codeUser.setUsername(username);
        try {
            codeUserRepository.save(codeUser);
        } catch (Exception e) {
            return new RedirectView("/?username=duplicate");

        }

        /// creates and a sets token we can hijak for sign up
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }
//    @GetMapping("/login")
//    public String showedLoginPage(){
//        return "login.html";
//    }
//

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }


    @GetMapping("/codeUser/{id}")
    public String showSingleUser(@PathVariable long id, Model m , Principal p){
       CodeUser codeUser = codeUserRepository.findById(id).get();
       m.addAttribute("codeUser", codeUser);
       if(p != null){
           CodeUser visitor = codeUserRepository.findByUsername(p.getName());
           if(!visitor.getUsername().equals(codeUser.getUsername())){
               m.addAttribute("visitor", visitor);
           }

       }
       else{
           CodeUser visitor = new CodeUser();
           visitor.setUsername("Guest");
           m.addAttribute("visitor",visitor);
       }
       return "codeUser.html";
    }

    @PutMapping("/codeUser/{id}")
    public RedirectView updateBio(@PathVariable long id, String bio){
        CodeUser codeUser = codeUserRepository.findById(id).get();
        codeUser.bio = bio;
        codeUserRepository.save(codeUser);
        return new RedirectView("/codeUser/" + id);
    }

}
