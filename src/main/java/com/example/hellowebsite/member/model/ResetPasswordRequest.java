package com.example.hellowebsite.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ResetPasswordRequest {

    private String userId;
    private String userName;

    private String password;
    private String id;
}
