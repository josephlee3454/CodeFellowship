package com.example.codefellowshiptwo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CodeUserController {
    @PostMapping("/code")
    public RedirectView createCodeUser(){
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showedLoginPage(){
        return "login.html";
    }
}
