package com.example.springbootsecurity.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @GetMapping("/home")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }
    @GetMapping("/user")
    public ModelAndView user(Principal principal) {
        System.out.println(principal.getName());
        return new ModelAndView("/user");
    }
    @GetMapping("/admin")
    public ModelAndView admin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println(securityContext.getAuthentication().getName());
        return new ModelAndView("/admin");
    }
}
