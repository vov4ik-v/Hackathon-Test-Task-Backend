package com.awl.hackathontesttaskbackend.service;


import com.awl.hackathontesttaskbackend.dto.user.UpdateEmailDto;
import com.awl.hackathontesttaskbackend.dto.user.UpdateOptionalUserInfoDto;
import com.awl.hackathontesttaskbackend.dto.user.UpdatePasswordDto;
import com.awl.hackathontesttaskbackend.dto.user.UserDto;
import com.awl.hackathontesttaskbackend.enums.AuthProvider;
import com.awl.hackathontesttaskbackend.enums.ERole;
import com.awl.hackathontesttaskbackend.exeptions.EmailAlreadyExistException;
import com.awl.hackathontesttaskbackend.exeptions.EmailNotFoundException;
import com.awl.hackathontesttaskbackend.exeptions.OldPasswordIsIncorrectException;
import com.awl.hackathontesttaskbackend.exeptions.UserNotExistException;
import com.awl.hackathontesttaskbackend.model.User;
import com.awl.hackathontesttaskbackend.repository.UserRepository;
import com.awl.hackathontesttaskbackend.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, EmailSenderService emailSenderService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getCurrentUserDto(Principal principal) {
        return getUserDtoByPrincipal(principal);
    }
    private UserDto getUserDtoByPrincipal(Principal principal) {
        String email = principal.getName();
        return userRepository.findUserDtoByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email " + email));
    }
    private User getUserByPrincipal(Principal principal) {
        String email = principal.getName();
        return userRepository.findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email " + email));
    }

    public void createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setImageUrl("https://w7.pngwing.com/pngs/612/280/png-transparent-customer-user-userphoto-account-person-glyphs-icon.png");
        user.setFirstName(userIn.getFirstName());
        user.setLastName(userIn.getLastName());
        user.setIsHelper(userIn.getIsHelper());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.USER);
        System.out.println(userRepository.findUserByEmail(user.getEmail()).isPresent());
        try {
            userRepository.save(user);
            LOG.info("Saving User {}", userIn.getEmail());
        } catch (Exception e) {
            LOG.error("Error during registration. {}",e.getMessage());
            throw new EmailAlreadyExistException("The user with email " + user.getEmail() + " already exist. Please check credentials");
        }


    }
    public User updateOptionalInfoUser(UpdateOptionalUserInfoDto updateOptionalUserInfoDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setImageUrl(updateOptionalUserInfoDto.getImageUrl());
        user.setPhoneNumber(updateOptionalUserInfoDto.getPhoneNumber());
        user.setFirstName(updateOptionalUserInfoDto.getFirstName());
        user.setLastName(updateOptionalUserInfoDto.getLastName());
        return userRepository.save(user);
    }

    public String updateEmail(UpdateEmailDto updateEmailDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        String email  = updateEmailDto.getEmail();
        boolean isPresent = userRepository.findUserByEmail(email).isPresent();
        if(!isPresent){
            user.setEmail(email);
            userRepository.save(user);
            return email;
        }
        else {
            throw new EmailAlreadyExistException("Email already used");
        }
    }


    public String updatePassword(UpdatePasswordDto updatePasswordDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        boolean isMatchesPassword = isTruePassword(updatePasswordDto,user);
        if (isMatchesPassword){
            user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
            return "Password change successfully";
        }
        else {
            throw new OldPasswordIsIncorrectException("Passwords didn`t matches");
        }
    }

    public boolean isTruePassword(UpdatePasswordDto updatePasswordDto,User user){
        if(user != null){
            return passwordEncoder.matches(user.getPassword(),updatePasswordDto.getOldPassword());
        }
        else {
            return false;
        }
    }
    public void forgotPassword(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user == null) {
            LOG.error("User with email:{} not found ", email);
            throw new UserNotExistException("User with email: "+email+" not found");
        } else {
            String resetPasswordToken = UUID.randomUUID().toString();
            setNewResetPasswordToken(resetPasswordToken, email);
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to AUDI. Please, visit next link: http://localhost:8080/forget/%s",
                    user.getUsername(),
                    resetPasswordToken
            );
            emailSenderService.sendMail(email, "FORGOT YOUR PASSWORD", message);
        }
    }
    public User getByResetToken(String resetPasswordToken) {
        return userRepository.findUserByResetPasswordToken(resetPasswordToken).orElseThrow(() -> new UserNotExistException("User with token: " + resetPasswordToken + " not found"));
    }
    private void setNewResetPasswordToken(String token, String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }

    }
    public void updateForgotPassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodePassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

}
