package com.awl.hackathontesttaskbackend.controller;


import com.awl.hackathontesttaskbackend.dto.EmailDto;
import com.awl.hackathontesttaskbackend.dto.user.UpdateEmailDto;
import com.awl.hackathontesttaskbackend.dto.user.UpdateOptionalUserInfoDto;
import com.awl.hackathontesttaskbackend.dto.user.UpdatePasswordDto;
import com.awl.hackathontesttaskbackend.dto.user.UserDto;
import com.awl.hackathontesttaskbackend.facade.UpdateOptionalUserInfoFacade;
import com.awl.hackathontesttaskbackend.model.User;
import com.awl.hackathontesttaskbackend.service.UserService;
import com.awl.hackathontesttaskbackend.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {



    private final UserService userService;
    private final UpdateOptionalUserInfoFacade updateOptionalUserInfoFacade;
    private final ResponseErrorValidation responseErrorValidation;
    @Autowired
    public UserController(UserService userService, UpdateOptionalUserInfoFacade updateOptionalUserInfoFacade, ResponseErrorValidation responseErrorValidation) {
        this.userService = userService;
        this.updateOptionalUserInfoFacade = updateOptionalUserInfoFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){
        UserDto userDto = userService.getCurrentUserDto(principal);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/update/optionalInfo")
    public ResponseEntity<Object> updateOptionalInfoUser(@Valid @RequestBody UpdateOptionalUserInfoDto updateOptionalUserInfoDto, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        User user = userService.updateOptionalInfoUser(updateOptionalUserInfoDto,principal);
        UpdateOptionalUserInfoDto userUpdated = updateOptionalUserInfoFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/update/email")
    public ResponseEntity<Object> updateEmail(@Valid@RequestBody UpdateEmailDto updateEmailDto, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        String email = userService.updateEmail(updateEmailDto,principal);
        return new ResponseEntity<>(email, HttpStatus.OK);

    }
    @PostMapping("/update/password")
    public ResponseEntity<Object> updatePassword(@Valid@RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        String response = userService.updatePassword(updatePasswordDto,principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailDto emailDto){
        userService.forgotPassword(emailDto.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/forgot/setNewPassword")
    public ResponseEntity<?> setNewPassword(@RequestParam String resetToken,@RequestParam String password){
        User user = userService.getByResetToken(resetToken);
        userService.updateForgotPassword(user,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
