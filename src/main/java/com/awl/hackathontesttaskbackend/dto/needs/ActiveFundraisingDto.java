package com.awl.hackathontesttaskbackend.dto.needs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveFundraisingDto {
    private String imageUrl;
    private String description;
    private Integer moneyGoal;
    private String goalName;
    private String needyThing;
    private String forWhom;
    private String donationUrl;
}
