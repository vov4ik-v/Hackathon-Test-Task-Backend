package com.awl.hackathontesttaskbackend.facade.needs;

import com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForPsychologicalSupport;
import org.springframework.stereotype.Component;


@Component
public class PsychologicalSupportFacade {
    public SpecificForPsychologicalSupport psychologicalSupportDtoToModel(PsychologicalSupportDto psychologicalSupportDto){
        SpecificForPsychologicalSupport specificForPsychologicalSupport = new SpecificForPsychologicalSupport();
        specificForPsychologicalSupport.setFirstName(psychologicalSupportDto.getFirstName());
        specificForPsychologicalSupport.setLastName(psychologicalSupportDto.getLastName());
        return specificForPsychologicalSupport;
    }
}
