package com.awl.hackathontesttaskbackend.model.needs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


//збори: сума, назва, опис, фото, що треба, для кого, профіль хто створив збір, дата

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificForActiveFundraisings {
    private Integer moneyGoal;
    private String goalName;
    private String needyThing;
    private String forWhom;
    private String donationUrl;
}

