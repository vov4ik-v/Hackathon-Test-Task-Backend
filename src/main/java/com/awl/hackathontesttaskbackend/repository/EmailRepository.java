package com.awl.hackathontesttaskbackend.repository;

import com.awl.hackathontesttaskbackend.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email,Integer> {
    Optional<Email> findById(Integer id);
    Optional<Email> findByEmail(String email);
}