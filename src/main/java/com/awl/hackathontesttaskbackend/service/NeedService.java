package com.awl.hackathontesttaskbackend.service;

import com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto;
import com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto;
import com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdateActiveFundraisingDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdateHumanitarianAidDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdatePsychologicalSupportDto;
import com.awl.hackathontesttaskbackend.enums.NeedType;
import com.awl.hackathontesttaskbackend.exeptions.IsNotYourNeedException;
import com.awl.hackathontesttaskbackend.exeptions.NeedNotFoundException;
import com.awl.hackathontesttaskbackend.facade.needs.ActiveFundraisingFacade;
import com.awl.hackathontesttaskbackend.facade.needs.HumanitarianAidFacade;
import com.awl.hackathontesttaskbackend.facade.needs.PsychologicalSupportFacade;
import com.awl.hackathontesttaskbackend.model.Need;
import com.awl.hackathontesttaskbackend.model.User;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForActiveFundraisings;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForHumanitarianAid;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForPsychologicalSupport;
import com.awl.hackathontesttaskbackend.repository.NeedRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.List;

@Service
public class NeedService {
    private final NeedRepository needRepository;
    private final ActiveFundraisingFacade activeFundraisingFacade;
    private final PsychologicalSupportFacade psychologicalSupportFacade;
    private final EmailSenderService emailSenderService;
    private final EmailService emailService;
    private final HumanitarianAidFacade humanitarianAidFacade;
    private final UserService userService;

    public NeedService(NeedRepository needRepository, ActiveFundraisingFacade activeFundraisingFacade, PsychologicalSupportFacade psychologicalSupportFacade, EmailSenderService emailSenderService, EmailService emailService, HumanitarianAidFacade humanitarianAidFacade, UserService userService) {
        this.needRepository = needRepository;
        this.activeFundraisingFacade = activeFundraisingFacade;
        this.psychologicalSupportFacade = psychologicalSupportFacade;
        this.emailSenderService = emailSenderService;
        this.emailService = emailService;
        this.humanitarianAidFacade = humanitarianAidFacade;
        this.userService = userService;
    }


    public  void sentEmailAboutNewNeedToAllEmails(NeedType needType, String description) throws MessagingException {
        List<String> emails = emailService.getAllEmails(emailService.getAll());
        emailSenderService.sendMailToManyPerson(emails,"New " + needType + " need in our site",description);


    }
    public void createActiveFundraising(ActiveFundraisingDto activeFundraisingDto, Principal principal) throws MessagingException {
        Need needToSave = new Need();
        needToSave.setImageUrl(activeFundraisingDto.getImageUrl());
        needToSave.setDescription(activeFundraisingDto.getDescription());
        needToSave.setStateOfNeed(true);
        needToSave.setAnnouncementMaker(userService.getCurrentUser(principal));
        needToSave.setSpecificForActiveFundraisings(activeFundraisingFacade.activeFundraisingDtoToModel(activeFundraisingDto));
        needToSave.setNeedType(NeedType.ACTIVE_FUNDRAISING);
        needRepository.save(needToSave);
        sentEmailAboutNewNeedToAllEmails(needToSave.getNeedType(),needToSave.getDescription());
    }

    public void createHumanitarianAid(HumanitarianAidDto humanitarianAidDto, Principal principal) throws MessagingException {
        Need needToSave = new Need();
        needToSave.setImageUrl(humanitarianAidDto.getImageUrl());
        needToSave.setDescription(humanitarianAidDto.getDescription());
        needToSave.setStateOfNeed(true);
        needToSave.setAnnouncementMaker(userService.getCurrentUser(principal));
        needToSave.setSpecificForHumanitarianAid(humanitarianAidFacade.humanitarianAidDtoToSpecificModel(humanitarianAidDto));
        needToSave.setNeedType(NeedType.HUMANITARIAN_AID);
        needRepository.save(needToSave);
        sentEmailAboutNewNeedToAllEmails(needToSave.getNeedType(),needToSave.getDescription());

    }

    public void createPsychologicalSupport(PsychologicalSupportDto psychologicalSupportDto, Principal principal) throws MessagingException {
        Need needToSave = new Need();
        needToSave.setImageUrl(psychologicalSupportDto.getImageUrl());
        needToSave.setDescription(psychologicalSupportDto.getDescription());
        needToSave.setStateOfNeed(true);
        needToSave.setAnnouncementMaker(userService.getCurrentUser(principal));
        needToSave.setSpecificForPsychologicalSupport(psychologicalSupportFacade.psychologicalSupportDtoToModel(psychologicalSupportDto));
        needToSave.setNeedType(NeedType.PSYCHOLOGICAL_SUPPORT);
        needRepository.save(needToSave);
        sentEmailAboutNewNeedToAllEmails(needToSave.getNeedType(),needToSave.getDescription());

    }

    public List<ActiveFundraisingDto> getAllActiveFundraisingDto() {
        return needRepository.findAllActiveFundraisingDtos();
    }

    public List<HumanitarianAidDto> getAllHumanitarianAidDto() {
        return needRepository.findAllHumanitarianAidDtos();
    }

    public List<PsychologicalSupportDto> getAllPsychologicalSupportDto() {
        return needRepository.findAllPsychologicalSupportDtos();
    }

    public void deleteNeed(Long activeFundraisingId, Principal principal) {
        User announcementMaker = userService.getCurrentUser(principal);
        Need needToDelete = getNeedById(activeFundraisingId);
        if (announcementMaker.getNeedList().contains(needToDelete)) {
            needRepository.delete(needToDelete);
        } else {
            throw new IsNotYourNeedException("You try to delete not your need");
        }
    }

    public UpdateHumanitarianAidDto updateHumanitarianAid(UpdateHumanitarianAidDto updateHumanitarianAidDto) {
        Need updatedNeed = getNeedById(updateHumanitarianAidDto.getId());
        updatedNeed.setStateOfNeed(updateHumanitarianAidDto.getStateOfNeed());
        updatedNeed.setImageUrl(updateHumanitarianAidDto.getImageUrl());
        updatedNeed.setDescription(updateHumanitarianAidDto.getDescription());
        SpecificForHumanitarianAid specificForHumanitarianAid = new SpecificForHumanitarianAid();
        specificForHumanitarianAid.setNeedName(updateHumanitarianAidDto.getNeedName());
        specificForHumanitarianAid.setCity(updateHumanitarianAidDto.getCity());
        updatedNeed.setSpecificForHumanitarianAid(specificForHumanitarianAid);
        needRepository.save(updatedNeed);
        return updateHumanitarianAidDto;

    }

    public UpdatePsychologicalSupportDto updatePsychologicalSupport(UpdatePsychologicalSupportDto updatePsychologicalSupportDto) {
        Need updatedNeed = getNeedById(updatePsychologicalSupportDto.getId());
        updatedNeed.setStateOfNeed(updatePsychologicalSupportDto.getStateOfNeed());
        updatedNeed.setImageUrl(updatePsychologicalSupportDto.getImageUrl());
        updatedNeed.setDescription(updatePsychologicalSupportDto.getDescription());
        SpecificForPsychologicalSupport specificForPsychologicalSupport =new SpecificForPsychologicalSupport();
        specificForPsychologicalSupport.setFirstName(updatePsychologicalSupportDto.getFirstName());
        specificForPsychologicalSupport.setLastName(updatePsychologicalSupportDto.getLastName());
        updatedNeed.setSpecificForPsychologicalSupport(specificForPsychologicalSupport);
        needRepository.save(updatedNeed);
        return updatePsychologicalSupportDto;

    }

    public UpdateActiveFundraisingDto updateActiveFundraising(UpdateActiveFundraisingDto updateActiveFundraisingDto) {
        Need updatedNeed =getNeedById(updateActiveFundraisingDto.getId());
        updatedNeed.setStateOfNeed(updateActiveFundraisingDto.getStateOfNeed());
        updatedNeed.setImageUrl(updateActiveFundraisingDto.getImageUrl());
        updatedNeed.setDescription(updateActiveFundraisingDto.getDescription());
        SpecificForActiveFundraisings specificForActiveFundraisings = new SpecificForActiveFundraisings();
        specificForActiveFundraisings.setNeedyThing(updateActiveFundraisingDto.getNeedyThing());
        specificForActiveFundraisings.setForWhom(updateActiveFundraisingDto.getForWhom());
        specificForActiveFundraisings.setMoneyGoal(updateActiveFundraisingDto.getMoneyGoal());
        specificForActiveFundraisings.setDonationUrl(updateActiveFundraisingDto.getDonationUrl());
        specificForActiveFundraisings.setGoalName(updateActiveFundraisingDto.getGoalName());
        updatedNeed.setSpecificForActiveFundraisings(specificForActiveFundraisings);
        needRepository.save(updatedNeed);
        return updateActiveFundraisingDto;

    }

    private Need getNeedById(Long updateActiveFundraisingDto) {
        return needRepository.findNeedById(updateActiveFundraisingDto).orElseThrow(() -> new NeedNotFoundException("Need with id:" + updateActiveFundraisingDto + " not found"));
    }
}
