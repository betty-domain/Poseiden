package com.poseiden.springboot.controllers;

import com.poseiden.springboot.domain.User;
import com.poseiden.springboot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller in charge of any request related to user entity
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * Return user's List page content
     *
     * @param model list of user
     * @return user's List page
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     * Return the add user page
     *
     * @param user user entity to add
     * @return add user page
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Create a user if valid
     *
     * @param user  user to create
     * @param result contains errors if user is not valid
     * @param model  list of user
     * @return list of user page if added user is valid, stay at user/add page otherwise
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Return filled update user page for a specific user
     *
     * @param id    id of user to update
     * @param model user to Update
     * @return update user page, can throw IllegalArgumentException if id of user is invalid
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Update a user if valid
     *
     * @param id      if of user to update
     * @param user user with modified values
     * @param result  contains errors if user is not invalid
     * @param model   list of user
     * @return list of user page if updated user is valid, stay at user/update page otherwise
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     * Delete user by Id
     *
     * @param id    id of user to delete
     * @param model List of user if id is valid
     * @return user's List page if id is valid, throw IllegalArgumentException otherwise
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
