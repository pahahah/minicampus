package com.example.hellowebsite.components;

import com.example.hellowebsite.member.entity.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MailComponents {
    private final JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    public boolean sendMail (String mail, String userName, String uuid, Email email){
        boolean result = false;

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", userName);
        variables.put("uuid", uuid);

        Context context = new Context();
        context.setVariables(variables);

        String subject = email.getEmailSubject();
        String text = htmlTemplateEngine.process(email.getEmailText(), context);

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        try {
            javaMailSender.send(msg);
            result = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
}
