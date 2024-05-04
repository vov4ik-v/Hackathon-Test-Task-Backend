package com.spm.vasylyshyn.facade;


import com.spm.vasylyshyn.dto.user.UpdateOptionalUserInfoDto;
import com.spm.vasylyshyn.dto.user.UserDto;
import com.spm.vasylyshyn.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateOptionalUserInfoFacade {
    public UpdateOptionalUserInfoDto userToUserDTO(User user){
        UpdateOptionalUserInfoDto updateOptionalUserInfoDto = new UpdateOptionalUserInfoDto();
        updateOptionalUserInfoDto.setImageUrl(user.getImageUrl());
        updateOptionalUserInfoDto.setPhoneNumber(user.getPhoneNumber());
        updateOptionalUserInfoDto.setLastName(user.getLastName());
        updateOptionalUserInfoDto.setFirstName(user.getFirstName());
        updateOptionalUserInfoDto.setDefaultAddressForNewDevices(user.getAddress());
        return updateOptionalUserInfoDto;
    }
}
