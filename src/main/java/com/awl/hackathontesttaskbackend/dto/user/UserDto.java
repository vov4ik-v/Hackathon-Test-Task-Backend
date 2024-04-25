package com.awl.hackathontesttaskbackend.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private String bio;
    private String firstName;
    private String lastName;
    private Boolean isHelper;

}

