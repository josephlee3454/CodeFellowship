package com.example.codefellowshiptwo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;



@Controller
public class CodeController {
    @GetMapping("/")
    public String showCodeHome(Principal p){ // Principal == the user
        System.out.println("p" + p);
        if(p != null){
            System.out.println("p.getName() = " + p.getName());
        }

        return "index";
    }

    @GetMapping("/codeUser")
    public String showCoder(Principal p, Model m){ // principal is like Model m. it represents the logged in user
        System.out.println("p.getName() = " + p.getName());

        m.addAttribute("user", p.getName());
        return "codeUser";
    }









//    @PostMapping("/codeUser")
//    public RedirectView createUser(){
//        return new RedirectView("/");
//    }
//
//
//    @GetMapping("/login")
//    public String showLoginPAge(){
//        return "login.html";
//    }

}
