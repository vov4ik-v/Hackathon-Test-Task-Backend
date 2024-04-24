package com.awl.hackathontesttaskbackend.service;

import com.awl.hackathontesttaskbackend.exeptions.EmailAlreadyExistException;
import com.awl.hackathontesttaskbackend.exeptions.EmailNotFoundException;
import com.awl.hackathontesttaskbackend.model.Email;
import com.awl.hackathontesttaskbackend.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void addEmail(Email email) {
        if(emailRepository.findByEmail(email.getEmail()).isEmpty()){
            emailRepository.save(email);
        }
        else{ throw new EmailAlreadyExistException("This email already added");
        }

    }

    public List<Email> getAll() {
        return emailRepository.findAll();
    }
    public void deleteEmail(Integer id){
        Email email = emailRepository.findById(id).orElseThrow(() ->new EmailNotFoundException("email not found"));
        emailRepository.delete(email);

    }

    public List<String> getAllEmails(List<Email> emailList) {
        List<String> emails = new ArrayList<>();
        for (Email email : emailList) {
            emails.add(email.getEmail());
        }
        return emails;
    }
}