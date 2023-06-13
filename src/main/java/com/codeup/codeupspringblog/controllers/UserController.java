package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository usersDao;
    private final PasswordEncoder encoder;

    public UserController(UserRepository usersDao, PasswordEncoder encoder) {
        this.usersDao = usersDao;
        this.encoder = encoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/sign-up")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registerUser(@ModelAttribute User user) {
        // hash the password.
        String hash = encoder.encode(user.getPassword());
        // set the hashed password BEFORE saving to the database.
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }

}