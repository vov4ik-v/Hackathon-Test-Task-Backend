package com.awl.hackathontesttaskbackend.facade.needs;

import com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdateHumanitarianAidDto;
import com.awl.hackathontesttaskbackend.model.Need;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForHumanitarianAid;
import org.springframework.stereotype.Component;

@Component
public class HumanitarianAidFacade {
    public SpecificForHumanitarianAid humanitarianAidDtoToSpecificModel(HumanitarianAidDto humanitarianAidDto){
        SpecificForHumanitarianAid specificForHumanitarianAid = new SpecificForHumanitarianAid();
        specificForHumanitarianAid.setCity(humanitarianAidDto.getCity());
        specificForHumanitarianAid.setNeedName(humanitarianAidDto.getNeedName());
        return specificForHumanitarianAid;
    }

//    public Need updateHumanitarianAidDtoToNeed(UpdateHumanitarianAidDto updateHumanitarianAidDto){
//        Need need = new Need();
//
//        need.setStateOfNeed(updateHumanitarianAidDto.getStateOfNeed());
//        need.setImageUrl(updateHumanitarianAidDto.getImageUrl());
//        need.setDescription(updateHumanitarianAidDto.getDescription());
//        SpecificForHumanitarianAid specificForHumanitarianAid = new SpecificForHumanitarianAid();
//        specificForHumanitarianAid.setNeedName(updateHumanitarianAidDto.getNeedName());
//        specificForHumanitarianAid.setCity(updateHumanitarianAidDto.getCity());
//        need.setSpecificForHumanitarianAid(specificForHumanitarianAid);
//        return need;
//    }
}
