package com.Spring.SpringBoot.services;

import com.Spring.SpringBoot.entity.ConfirmationToken;
import com.Spring.SpringBoot.entity.User;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service("emailSenderService")
public class EmailSenderService {

    @Autowired
    private Configuration config;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user,ConfirmationToken token) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Successfull registration email");
        helper.setTo(user.getEmail());
        String emailContent = getEmailContent(user,token);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailContent(User user, ConfirmationToken token) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user.getFirstname()+" "+user.getLastname());
        model.put("token",token.getConfirmationToken());
        config.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public void sendEmail(String email, String token)throws MessagingException, IOException, TemplateException {
        //User user=new User();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Reset Password Email");
        helper.setTo(email);
        String emailContent = getEmailContent(email,token);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    String getEmailContent(String email, String token) throws IOException, TemplateException {

        User user=new User();
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", email);
        model.put("token",token);
        config.getTemplate("ResetEmail.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

}
