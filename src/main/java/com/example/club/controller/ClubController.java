package com.example.club.controller;

import com.example.club.model.Club;
import com.example.club.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping("/Creation")
    public String showCreationForm(Model model) {
        model.addAttribute("club", new Club());
        return "Creation";
    }

    @PostMapping("/Creation")
    public String createClub(
            @Valid @ModelAttribute("club") Club club,
            BindingResult result,
            @RequestParam(value = "members", required = false) List<String> members,
            @RequestParam("logoFile") MultipartFile logoFile) throws IOException {

        if (result.hasErrors()) {
            return "Creation";
        }

        if (logoFile != null && !logoFile.isEmpty()) {
            String fileName = logoFile.getOriginalFilename();
            String logoPath = "src/main/resources/static/pictures/" + fileName;
            Files.write(Paths.get(logoPath), logoFile.getBytes());
            club.setLogo("/pictures/" + fileName);
        }

        club.setMembers(members != null ? members : List.of());
        clubRepository.save(club);
        return "redirect:/?successMessage=Le club a ete cree avec succes !";
    }
}