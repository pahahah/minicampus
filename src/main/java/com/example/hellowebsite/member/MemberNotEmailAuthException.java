package com.example.hellowebsite.member;

public class MemberNotEmailAuthException extends RuntimeException{
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
