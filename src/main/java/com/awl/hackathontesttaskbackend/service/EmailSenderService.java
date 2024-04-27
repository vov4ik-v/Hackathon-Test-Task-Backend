package com.awl.hackathontesttaskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Objects;

@Service
public class EmailSenderService {


    @Autowired
    private JavaMailSender javaMailSender;

    private final Environment env;

    public EmailSenderService(Environment env) {
        this.env = env;
    }


    public void sendMail(String toMail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);

    }

    public void sendMailToManyPerson(List<String> emailList, String subject, String message) throws MessagingException {
        String[] emails =listToArray(emailList);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emails);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        javaMailSender.send(mailMessage);

    }
    private String[] listToArray(List<String> emailList){
        String[] emails = new String[emailList.size()];
        for (int i = 0; i<emailList.size();i++){
            emails[i] = emailList.get(i);
        }
        return emails;

    }
}