package com.awl.hackathontesttaskbackend.facade.needs;


import com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForActiveFundraisings;
import org.springframework.stereotype.Component;

@Component
public class ActiveFundraisingFacade {
    public SpecificForActiveFundraisings activeFundraisingDtoToModel(ActiveFundraisingDto activeFundraisingDto){
        SpecificForActiveFundraisings specificForActiveFundraisings = new SpecificForActiveFundraisings();
        specificForActiveFundraisings.setForWhom(activeFundraisingDto.getForWhom());
        specificForActiveFundraisings.setGoalName(activeFundraisingDto.getGoalName());
        specificForActiveFundraisings.setDonationUrl(activeFundraisingDto.getDonationUrl());
        specificForActiveFundraisings.setMoneyGoal(activeFundraisingDto.getMoneyGoal());
        specificForActiveFundraisings.setNeedyThing(specificForActiveFundraisings.getNeedyThing());
        return specificForActiveFundraisings;
    }
}
