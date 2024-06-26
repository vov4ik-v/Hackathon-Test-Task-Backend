package com.awl.hackathontesttaskbackend.repository;


import com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto;
import com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto;
import com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto;
import com.awl.hackathontesttaskbackend.model.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeedRepository extends JpaRepository<Need,Long> {



    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto(" +
            "u.imageUrl, u.description,u.specificForActiveFundraisings.moneyGoal,u.specificForActiveFundraisings.goalName,u.specificForActiveFundraisings.needyThing,u.specificForActiveFundraisings.forWhom,u.specificForActiveFundraisings.donationUrl) from Need u where u.needType = 'ACTIVE_FUNDRAISING' ")
    List<ActiveFundraisingDto> findAllActiveFundraisingDtos();


    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto(" +
            "u.imageUrl, u.description,u.specificForHumanitarianAid.needName,u.specificForHumanitarianAid.city) from Need u where  u.needType = 'HUMANITARIAN_AID'")
    List<HumanitarianAidDto> findAllHumanitarianAidDtos();


    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto(" +
            "u.imageUrl, u.description,u.specificForPsychologicalSupport.firstName,u.specificForPsychologicalSupport.lastName) from Need u where u.needType = 'PSYCHOLOGICAL_SUPPORT'")
    List<PsychologicalSupportDto> findAllPsychologicalSupportDtos();



    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto(" +
            "u.imageUrl, u.description,u.specificForActiveFundraisings.moneyGoal,u.specificForActiveFundraisings.goalName,u.specificForActiveFundraisings.needyThing,u.specificForActiveFundraisings.forWhom,u.specificForActiveFundraisings.donationUrl) from Need u where u.needType = 'ACTIVE_FUNDRAISING' order by u.createdDate desc ")
    List<ActiveFundraisingDto> findAllActiveFundraisingSortedDesc();

    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto(" +
            "u.imageUrl, u.description,u.specificForActiveFundraisings.moneyGoal,u.specificForActiveFundraisings.goalName,u.specificForActiveFundraisings.needyThing,u.specificForActiveFundraisings.forWhom,u.specificForActiveFundraisings.donationUrl) from Need u where u.needType = 'ACTIVE_FUNDRAISING' ORDER BY u.createdDate asc")
    List<ActiveFundraisingDto> findAllActiveFundraisingSortedAsc();

    Optional<Need> findNeedById(Long id);
    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto(" +
            "u.imageUrl, u.description,u.specificForPsychologicalSupport.firstName,u.specificForPsychologicalSupport.lastName) from Need u where u.needType = 'PSYCHOLOGICAL_SUPPORT' ORDER BY u.createdDate desc ")
    List<PsychologicalSupportDto> findAllPsychologicalSupportSortedDesc();
    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto(" +
            "u.imageUrl, u.description,u.specificForPsychologicalSupport.firstName,u.specificForPsychologicalSupport.lastName) from Need u where u.needType = 'PSYCHOLOGICAL_SUPPORT' ORDER BY u.createdDate asc")
    List<PsychologicalSupportDto> findAllPsychologicalSupportSortedAsc();

    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto(" +
            "u.imageUrl, u.description,u.specificForHumanitarianAid.needName,u.specificForHumanitarianAid.city) from Need u where  u.needType = 'HUMANITARIAN_AID'ORDER BY u.createdDate desc ")
    List<HumanitarianAidDto> findAllHumanitarianAidSortedDesc();

    @Query(value = "select new com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto(" +
            "u.imageUrl, u.description,u.specificForHumanitarianAid.needName,u.specificForHumanitarianAid.city) from Need u where  u.needType = 'HUMANITARIAN_AID'ORDER BY u.createdDate asc")
    List<HumanitarianAidDto> findAllHumanitarianAidSortedAsc();
}
