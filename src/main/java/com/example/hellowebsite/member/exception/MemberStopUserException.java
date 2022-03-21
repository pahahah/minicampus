package com.example.hellowebsite.member.exception;

public class MemberStopUserException  extends RuntimeException{
    public MemberStopUserException(String error) {
        super(error);
    }
}
