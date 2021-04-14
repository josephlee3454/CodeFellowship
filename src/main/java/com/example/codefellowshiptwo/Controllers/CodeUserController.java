package com.example.codefellowshiptwo.Controllers;

import com.example.codefellowshiptwo.Models.user.CodeUser;
import com.example.codefellowshiptwo.Models.user.CodeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.GeneratedValue;

@Controller
public class CodeUserController {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CodeUserRepository codeUserRepository;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//
//
    @PostMapping("/codeUser")
    public RedirectView createUser(String username, String password) {
        password = passwordEncoder.encode(password);
        System.out.println("password = " + password);
        CodeUser codeUser = new CodeUser();
        codeUser.setPassword(password);
        codeUser.setUsername(username);

        codeUserRepository.save(codeUser);
        return new RedirectView("/");
    }


    @GetMapping("/login")
    public String showedLoginPage(){
        return "login.html";
    }

//
//    @GetMapping("/login")
//    public String showLoginPage(){
//        return "login.html";
//    }
//
//
//    @GetMapping("/codeUser/{id")
//    public String showSingUser(@PathVariable long id, Model m , Principal p){
//       CodeUser codeUser = codeUserRepository.findById(id).get();
//       m.addAttribute("codeUser", codeUser);
//       if(p != null){
//           CodeUser visitor = codeUserRepository.findByUsername(p.getName());
//           if(!visitor.getUsername().equals(codeUser.getUsername())){
//               m.addAttribute("visitor", visitor);
//           }
//
//       }
//       else{
//           CodeUser visitor = new CodeUser();
//           visitor.setUsername("Guest");
//           m.addAttribute("visitor",visitor);
//       }
//       return "codeUser.html";
//    }
//
//    @PutMapping("/codeUser/{id}")
//    public RedirectView updateBio(@PathVariable long id, String bio){
//        CodeUser dinoUser = CodeUserRepository.findById(id).get();
//        CodeUser.bio = bio;
//        codeUserRepository.save(CodeUser);
//        return new RedirectView("/dinosaurUser/" + id);
//    }

}
