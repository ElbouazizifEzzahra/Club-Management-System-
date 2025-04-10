package com.example.club.repository;

//import java.util.Optional;
import com.example.club.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {

}