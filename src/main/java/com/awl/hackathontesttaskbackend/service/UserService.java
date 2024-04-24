package com.awl.hackathontesttaskbackend.service;


import com.awl.hackathontesttaskbackend.dto.UserDto;
import com.awl.hackathontesttaskbackend.enums.ERole;
import com.awl.hackathontesttaskbackend.exeptions.EmailExistException;
import com.awl.hackathontesttaskbackend.exeptions.EmailNotFoundException;
import com.awl.hackathontesttaskbackend.model.User;
import com.awl.hackathontesttaskbackend.repository.UserRepository;
import com.awl.hackathontesttaskbackend.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getCurrentUserDto(Principal principal) {
        return getUserDtoByPrincipal(principal);
    }
    private UserDto getUserDtoByPrincipal(Principal principal) {
        String email = principal.getName();
        return userRepository.findUserDtoByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email " + email));
    }

    public void createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setImageUrl("https://w7.pngwing.com/pngs/612/280/png-transparent-customer-user-userphoto-account-person-glyphs-icon.png");
        user.setFirstName(userIn.getFirstName());
        user.setLastName(userIn.getLastName());
        user.setIsHelper(userIn.getIsHelper());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.USER);
        try {
            userRepository.save(user);
            LOG.info("Saving User {}", userIn.getEmail());
        } catch (Exception e) {
            LOG.error("Error during registration. {}",e.getMessage());
            throw new EmailExistException("The user with email" + user.getEmail() + " already exist. Please check credentials");
        }


    }
}
