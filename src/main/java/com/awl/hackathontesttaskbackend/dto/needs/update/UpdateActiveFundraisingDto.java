package com.awl.hackathontesttaskbackend.dto.needs.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateActiveFundraisingDto {
    private Long id;
    private String imageUrl;
    private String description;
    private Integer moneyGoal;
    private String goalName;
    private String needyThing;
    private String forWhom;
    private String donationUrl;
    private Boolean stateOfNeed;
}
