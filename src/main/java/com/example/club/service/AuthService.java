package com.example.club.service;

import com.example.club.model.User;
import com.example.club.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
///import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Injection de UserRepository

    @Autowired
    private PasswordEncoder passwordEncoder;
    // Méthode pour vérifier l'utilisateur et le mot de passe
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("DEBUG: Aucun utilisateur trouvé avec l'email: " + email); // Log de débogage
                    return new UsernameNotFoundException("Email non trouvé");
                });

        System.out.println("DEBUG: Utilisateur trouvé: " + user.getEmail()); // Confirmation

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }


    /*public boolean checkUserCredentials(String email, String password) {
        // Trouver l'utilisateur par email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return passwordEncoder.matches(password, user.getPassword());
        // Vérifier si le mot de passe correspond

    }*/
    @Transactional(readOnly = true)
    public User authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean checkUserCredentials(String email, String password) {
        return userRepository.existsByEmailAndPassword(email, password);
    }


    // Méthode pour hacher un mot de passe (utilisée lors de l'inscription)
    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
    /*public User registerUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
*/
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }







    //private PasswordEncoder passwordEncoder;

  /*  // Méthode pour l'inscription
    public User registerUser(User user) {
        // Utilisez l'instance userRepository pour appeler findByEmail
        //if (userRepository.findByEmail(user.getEmail()).isPresent()) {
          //  throw new RuntimeException("Cet email est déjà utilisé !");
        user.setPassword(passwordService.hashPassword(user.getPassword()));
        return userRepository.save(user);
        }
       // String hashedPassword = passwordEncoder.encode(user.getPassword());
       // user.setPassword(hashedPassword);

     //   return userRepository.save(user);
    //}

    // Méthode pour la connexion
    public User login(String email, String password) {
        // Trouver l'utilisateur par email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email non trouvé !"));

      //  if (!passwordEncoder.matches(password, user.getPassword())) {
         //   throw new RuntimeException("Mot de passe incorrect !");
       // }
       // if (!user.checkPassword(password)) {
            //throw new RuntimeException("Mot de passe incorrect");
        //}
        if (!passwordService.checkPassword(password, user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        return user;
    }*/
}