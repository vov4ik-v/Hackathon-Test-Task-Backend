package com.spm.vasylyshyn.controller;



import com.spm.vasylyshyn.dto.user.*;
import com.spm.vasylyshyn.response.ApiResponse;
import com.spm.vasylyshyn.dto.device.DeviceDto;
import com.spm.vasylyshyn.request.RegisterDeviceRequest;
import com.spm.vasylyshyn.validations.ResponseErrorValidation;
import com.spm.vasylyshyn.facade.UpdateOptionalUserInfoFacade;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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


//    TODO:Refactor it
    @GetMapping("/getDevicesForCurrentUser")
    public ResponseEntity<List<DeviceDto>> getDevicesForCurrentUser(Principal principal){
        List<DeviceDto> deviceDtoList = userService.getDeviceForCurrentUser(principal);
        return new ResponseEntity<>(deviceDtoList,HttpStatus.OK);
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
    @PostMapping("/update/username")
    public ResponseEntity<Object> updateUsername(@Valid@RequestBody UpdateUsernameDto updateUsernameDto, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        String username = userService.updateUsername(updateUsernameDto,principal);
        return new ResponseEntity<>(username, HttpStatus.OK);

    }

    @PostMapping("/update/password")
    public ResponseEntity<Object> updatePassword(@Valid@RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        String response = userService.updatePassword(updatePasswordDto,principal);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/addDeviceToUser")
        public ResponseEntity<ApiResponse> addDeviceToUser(@RequestBody RegisterDeviceRequest registerDeviceRequest, Principal principal){
           ApiResponse response =  userService.registerDevice(registerDeviceRequest, principal);
           return new ResponseEntity<>(response,HttpStatus.OK);

        }

}


