package com.example.hellowebsite.member.controller;

import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.model.ResetPasswordRequest;
import com.example.hellowebsite.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

       return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(){
        return "member/info";

    }

    @RequestMapping("/member/login")
    public String login() {

        return "member/login";
    }

    @GetMapping("/member/reset-password")
    public String resetPassword() {
        return "member/reset_password";
    }

    @PostMapping("/member/reset-password")
    public String resetPasswordSubmit(Model model, ResetPasswordRequest parameter) {
        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        }catch (Exception e){

        }

        model.addAttribute("result", result);
        return "member/reset_password_result";
    }

    @GetMapping("/member/new-password")
    public String newPassword(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("id");
        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);
        return "member/new_password";
    }

    @PostMapping("/member/new-password")
    public String newPasswordSubmit(Model model, ResetPasswordRequest parameter){
        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        }catch (Exception e){
        }

        model.addAttribute("result", result);

        return "member/new_password_result";
    }






}
