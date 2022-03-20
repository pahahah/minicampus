package com.example.hellowebsite.member.service;

import com.example.hellowebsite.components.MailComponents;
import com.example.hellowebsite.member.MemberNotEmailAuthException;
import com.example.hellowebsite.member.entity.Email;
import com.example.hellowebsite.member.entity.Member;
import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.model.ResetPasswordInput;
import com.example.hellowebsite.member.repository.EmailRepository;
import com.example.hellowebsite.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthKey(uuid)
                .emailAuthYn(false)

                .build();

        memberRepository.save(member);

        String emailId = "emailAuth";
        Optional<Email> optionalEmail = emailRepository.findById(emailId);
        Email eMail;
        if (optionalEmail.isPresent()){
            eMail = optionalEmail.get();
        }else {
            throw new NullPointerException("There is no email data.");
        }

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

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {
        Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("Member information does not exist.");
        }

        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));
        memberRepository.save(member);


        String emailId = "resetPassword";
        Optional<Email> optionalEmail = emailRepository.findById(emailId);
        Email email;
        if (optionalEmail.isPresent()){
            email = optionalEmail.get();
        }else {
            throw new NullPointerException("There is no password reset guide email data.");
        }

        String mail = parameter.getUserId();

        String userName = parameter.getUserName();
        mailComponents.sendMail(mail, userName ,uuid, email);

        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(username);
        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("No such a user");
        }
        Member member = optionalMember.get();

        if(!member.isEmailAuthYn()){
            throw new MemberNotEmailAuthException("Please login after email activation.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}
