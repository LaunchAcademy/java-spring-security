package com.launchacademy.springsecurityintro.controllers;

import com.launchacademy.springsecurityintro.models.User;
import com.launchacademy.springsecurityintro.security.SecurityService;
import com.launchacademy.springsecurityintro.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("userForm", new User());

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    return "security/registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "registration";
    }

    userService.save(userForm);

    securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

    return "redirect:/welcome";
  }

  @GetMapping("/login")
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    return "security/login";
  }

  @GetMapping({"/", "/welcome"})
  public String welcome(Model model, Authentication authentication) {
    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
    model.addAttribute("username", user.getUsername());
    return "root/welcome";
  }
}
