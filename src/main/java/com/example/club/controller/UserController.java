package com.example.club.controller;

import com.example.club.model.User;
import com.example.club.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


//import javax.security.sasl.AuthenticationException;
//import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
   
    public UserController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }
    

    @GetMapping("/register")
    public String registerForm() {
        return "inscrire";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateNaissance,
            @RequestParam String email,
            @RequestParam String password) {

        User user = new User();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setDateNaissance(dateNaissance);
        user.setEmail(email);
       // user.setPassword(password);
        user.setPassword(passwordEncoder.encode(password));

        authService.registerUser(user);
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login";
    }
    @GetMapping("/logout")
    public String displayLoginForm() {
        return "redirect:/Login";
    }

   /* @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {

        try {
            // L'authentification est maintenant gérée par Spring Security
            request.Login(email, password);
            return "redirect:/Accueil";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Email ou mot de passe invalide");
            return "Login";
        }
    }
    @GetMapping("/Accueil")
    public String accueil(HttpSession session) {

        // Vérification explicite de l'authentification
      //  if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
          return "Accueil";
        }*/
       // return "redirect:/login";
    }
   /* public String accueil(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "Accueil";
    }*/
   /*  @PostMapping("/logout") // ✅ Gère POST /logout
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/Login";
    }*/
    
