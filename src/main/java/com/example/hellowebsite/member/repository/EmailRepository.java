package com.example.hellowebsite.member.repository;

import com.example.hellowebsite.member.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, String> {
}
