package com.poseiden.springboot.controllers;

import com.poseiden.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public ModelAndView login() {
        //TODO : comment rediriger vers la page de login créer par Spring Security (formlogin())?
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login.html");
        return mav;
    }

    @GetMapping("/login-error")
    public ModelAndView loginError() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loginError", true);
        mav.setViewName("login.html");
        return mav;
    }

    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
