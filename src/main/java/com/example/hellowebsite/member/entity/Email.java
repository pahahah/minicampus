package com.example.hellowebsite.member.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Email {
    @Id
    private String emailId;
    private String emailSubject;
    private String emailText;
}
