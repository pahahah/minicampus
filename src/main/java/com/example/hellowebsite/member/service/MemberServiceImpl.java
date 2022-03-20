package com.example.hellowebsite.member.service;

import com.example.hellowebsite.components.MailComponents;
import com.example.hellowebsite.member.entity.EMail;
import com.example.hellowebsite.member.entity.Member;
import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.repository.EmailRepository;
import com.example.hellowebsite.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final EmailRepository emailRepository;
    private final MailComponents mailComponents;

    @Override
    public boolean register(MemberInput parameter) {
        Optional<Member> optionalMember =  memberRepository.findById(parameter.getUserId());
        if(optionalMember.isPresent()){
            return false;
        }

        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                .password(parameter.getPassword())
                .regDt(LocalDateTime.now())
                .emailAuthKey(uuid)
                .emailAuthYn(false)

                .build();

        memberRepository.save(member);

        String emailId = "emailAuth";
        Optional<EMail> optionalEmail = emailRepository.findById(emailId);
        EMail eMail = optionalEmail.get();

        String mail = parameter.getUserId();
        String userName = parameter.getUserName();

        mailComponents.sendMail(mail, userName, uuid, eMail);


        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()){
            return false;
        }
        Member member = optionalMember.get();
        if (member.isEmailAuthYn()) {
            return false;
        }

        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }


}
