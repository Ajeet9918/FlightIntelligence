package com.airline.controller;

import com.airline.dto.RegisterDto;
import com.airline.model.User;
import com.airline.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ── Register ──────────────────────────────────────────────────
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegister(
            @Valid @ModelAttribute("registerDto") RegisterDto dto,
            BindingResult result,
            Model model) {

        // Return to form if validation errors
        if (result.hasErrors()) {
            return "auth/register";
        }

        try {
            userService.register(dto);
            return "redirect:/login?registered=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "auth/register";
        }
    }

    // ── Login ─────────────────────────────────────────────────────
    // POST /login is handled automatically by Spring Security
    @GetMapping("/login")
    public String showLogin(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            @RequestParam(required = false) String registered,
            Model model) {

        if (error != null)      model.addAttribute("errorMsg", "Invalid email or password.");
        if (logout != null)     model.addAttribute("successMsg", "You have been logged out.");
        if (registered != null) model.addAttribute("successMsg", "Account created! Please log in.");

        return "auth/login";
    }

    // ── Dashboard ─────────────────────────────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        return "dashboard";
    }
}
