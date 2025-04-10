package com.example.club.controller;
// Dans HomeController.java ou UserController.java
import com.example.club.model.User;
import com.example.club.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

   /* @GetMapping("/")
    public String home() {
        return "Accueil";
    }
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /*@GetMapping("/Accueil") // Utilisez des minuscules pour l'URL
    public String accueil(Model model) {
        // Récupérer le nom d'utilisateur de l'utilisateur authentifié
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Charger l'objet User depuis la base de données
        User user = userService.findByEmail(email);

        // Injecter l'utilisateur dans le modèle
        model.addAttribute("user", user);

        return "Accueil"; // Nom du template (Accueil.html)
    }*/
    @GetMapping("/Accueil")
    public String accueil(Model model) {
        User user = userService.getCurrentUser(); // Exemple pour récupérer l'utilisateur connecté
        model.addAttribute("user", user); // Ajouter l'utilisateur au modèle
        return "Accueil"; // Retourne le template Accueil.html
    }
}