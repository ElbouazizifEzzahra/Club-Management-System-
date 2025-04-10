package com.example.club.repository;
import com.example.club.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
   /* User findByNom(String nom);*/
    boolean existsByEmailAndPassword(String email, String password);

}