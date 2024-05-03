package com.spm.vasylyshyn.service;


import com.spm.vasylyshyn.dto.user.*;
import com.spm.vasylyshyn.exeptions.*;
import com.spm.vasylyshyn.request.RegisterDeviceRequest;
import com.spm.vasylyshyn.response.ApiResponse;
import com.spm.vasylyshyn.dto.device.DeviceDto;
import com.spm.vasylyshyn.enums.CounterType;
import com.spm.vasylyshyn.enums.ERole;
import com.spm.vasylyshyn.facade.DeviceFacade;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.User;
import com.spm.vasylyshyn.repository.DeviceRepository;
import com.spm.vasylyshyn.repository.UserRepository;
import com.spm.vasylyshyn.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceFacade deviceFacade;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, DeviceRepository deviceRepository, DeviceFacade deviceFacade, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceFacade = deviceFacade;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAllByOrderByCreatedDateDesc();
    }

    public void createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setImageUrl("https://w7.pngwing.com/pngs/612/280/png-transparent-customer-user-userphoto-account-person-glyphs-icon.png");
        user.setUsername(userIn.getUsername());
//        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.USER);
        try {
            LOG.info("Saving User {}" + userIn.getEmail());
        } catch (Exception e) {
            LOG.error("Error during registration. {}" + e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }


    }


    public User updateOptionalInfoUser(UpdateOptionalUserInfoDto updateOptionalUserInfoDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setImageUrl(updateOptionalUserInfoDto.getImageUrl());
        user.setPhoneNumber(updateOptionalUserInfoDto.getPhoneNumber());
        user.setAddress(updateOptionalUserInfoDto.getDefaultAddressForNewDevices());
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
    public String updateUsername(UpdateUsernameDto updateUsernameDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        String username  = updateUsernameDto.getUsername();
        boolean isPresent = userRepository.findUserByUsername(username).isPresent();
        if(!isPresent){
            user.setUsername(username);
            userRepository.save(user);
            return username;
        }
        else {
            throw new UsernameAlreadyExistException("Username already used");
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
           throw new OldPasswordIsIncorectException("Passwords didn`t matches");
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

    public UserDto getCurrentUserDto(Principal principal) {
        return getUserDtoByPrincipal(principal);
    }
    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private UserDto getUserDtoByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserDtoByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }

    public List<DeviceDto> getDeviceForCurrentUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Device> devices = user.getDeviceList();
        return devices.stream().map(deviceFacade::deviceToDeviceDTO).collect(Collectors.toList());
    }

    public ApiResponse registerDevice(RegisterDeviceRequest registerDeviceRequest, Principal principal) {
        User user = getUserByPrincipal(principal);
        Device device = deviceRepository.findDeviceBySerialNumber(registerDeviceRequest.getSerialNumber()).orElseThrow(()-> new DeviceHasNotYetBeenCreatedException("Device has not yet been created"));
        device.setOwner(user);
        user.getDeviceList().add(device);
        userRepository.save(user);
        deviceRepository.save(device);
        return new ApiResponse(true,"Device added successfully");
    }


// Затичка, після міграції переробити
//    public ApiResponse addDeviceToUser(Long deviceNumber, CounterType deviceType, String address,
////                                       String password,
//                                       Principal principal) {
//        User user = getUserByPrincipal(principal);
//        Device device = deviceService.registerDevice(new DeviceDto(deviceNumber, "", null, address, 0L, deviceType, 0, LocalDateTime.now()));
////        if (device.getPassword().equals(password)){
//            device.setCounterType(deviceType);
//            device.setOwner(user);
//            user.getDeviceList().add(device);
//            userRepository.save(user);
//            deviceRepository.save(device);
//
//            return new ApiResponse(true,"Device added successfully");
////        }
//        else{
//            return new ApiResponse(true,"Please write correct number or password");
//        }


}