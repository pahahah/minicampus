package com.example.hellowebsite.member.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class EMail {
    @Id
    private String emailId;
    private String emailSubject;
    private String emailText;
}
