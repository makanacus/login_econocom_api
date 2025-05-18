package com.technical_proof.loginbackend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.technical_proof.loginbackend.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
