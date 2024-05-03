package com.spm.vasylyshyn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalAccount {

    @Embedded
    private Address address;
    private String phoneNumber;
}
