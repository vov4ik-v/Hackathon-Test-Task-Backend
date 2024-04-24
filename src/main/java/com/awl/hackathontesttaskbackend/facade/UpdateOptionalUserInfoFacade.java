package com.awl.hackathontesttaskbackend.facade;



import com.awl.hackathontesttaskbackend.dto.user.UpdateOptionalUserInfoDto;
import com.awl.hackathontesttaskbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateOptionalUserInfoFacade {

    public UpdateOptionalUserInfoDto userToUserDTO(User user){
        UpdateOptionalUserInfoDto updateOptionalUserInfoDto = new UpdateOptionalUserInfoDto();
        updateOptionalUserInfoDto.setImageUrl(user.getImageUrl());
        updateOptionalUserInfoDto.setPhoneNumber(user.getPhoneNumber());
        updateOptionalUserInfoDto.setLastName(user.getLastName());
        updateOptionalUserInfoDto.setFirstName(user.getFirstName());
        return updateOptionalUserInfoDto;
    }
}
