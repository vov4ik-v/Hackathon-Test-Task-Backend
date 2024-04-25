package com.awl.hackathontesttaskbackend.model.needs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SpecificForHumanitarianAid {
    private String needName;
    private String city;

}
