package com.awl.hackathontesttaskbackend.controller;


import com.awl.hackathontesttaskbackend.dto.needs.ActiveFundraisingDto;
import com.awl.hackathontesttaskbackend.dto.needs.HumanitarianAidDto;
import com.awl.hackathontesttaskbackend.dto.needs.PsychologicalSupportDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdateActiveFundraisingDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdateHumanitarianAidDto;
import com.awl.hackathontesttaskbackend.dto.needs.update.UpdatePsychologicalSupportDto;
import com.awl.hackathontesttaskbackend.response.MessageResponse;
import com.awl.hackathontesttaskbackend.service.NeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/needs")
@CrossOrigin
public class NeedController {
    private final NeedService needService;

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    public NeedController(NeedService needService) {
        this.needService = needService;
    }


    @PostMapping("/create/activeFundraising")
    private ResponseEntity<MessageResponse> createActiveFundraising(@RequestBody ActiveFundraisingDto activeFundraisingDto, Principal principal){
        needService.createActiveFundraising(activeFundraisingDto,principal);
        return new ResponseEntity<>(new MessageResponse("Active Fundraising created successfully"), HttpStatus.CREATED);
    }
    @GetMapping("/get/activeFundraising")
    private ResponseEntity<List<ActiveFundraisingDto>> getActiveFundraisings(){
        List<ActiveFundraisingDto> activeFundraisingDtoList = needService.getAllActiveFundraisingDto();
        return new ResponseEntity<>(activeFundraisingDtoList,HttpStatus.OK);
    }
    @PostMapping("/update/activeFundraising")
    private ResponseEntity<UpdateActiveFundraisingDto> updateActiveFundraising(@RequestBody UpdateActiveFundraisingDto updateActiveFundraisingDto){
        UpdateActiveFundraisingDto updatedActiveFundraising = needService.updateActiveFundraising(updateActiveFundraisingDto);
        return new ResponseEntity<>(updatedActiveFundraising,HttpStatus.OK);

    }
    @DeleteMapping("/delete/activeFundraising/{activeFundraisingId}")
    private ResponseEntity<MessageResponse> deleteActiveFundraising(@PathVariable("activeFundraisingId") Long activeFundraisingId, Principal principal){
        needService.deleteNeed(activeFundraisingId,principal);
        return new ResponseEntity<>(new MessageResponse("Active Fundraising deleted successfully"), HttpStatus.OK);
    }


    @PostMapping("/create/humanitarianAid")
    private ResponseEntity<MessageResponse> createHumanitarianAid(@RequestBody HumanitarianAidDto humanitarianAidDto, Principal principal){
        needService.createHumanitarianAid(humanitarianAidDto,principal);
        return new ResponseEntity<>(new MessageResponse("Humanitarian Aid created successfully"), HttpStatus.CREATED);
    }
    @GetMapping("/get/humanitarianAid")
    private ResponseEntity<List<HumanitarianAidDto>> getHumanitarianAids(){
        List<HumanitarianAidDto> humanitarianAidDtoList = needService.getAllHumanitarianAidDto();
        return new ResponseEntity<>(humanitarianAidDtoList,HttpStatus.OK);
    }
    @PostMapping("/update/humanitarianAid")
    private ResponseEntity<UpdateHumanitarianAidDto> updateActiveFundraising(@RequestBody UpdateHumanitarianAidDto updateHumanitarianAidDto){
        UpdateHumanitarianAidDto updatedHumanitarianAid = needService.updateHumanitarianAid(updateHumanitarianAidDto);
        return new ResponseEntity<>(updatedHumanitarianAid,HttpStatus.OK);

    }
    @DeleteMapping("/delete/humanitarianAid/{humanitarianAidId}")
    private ResponseEntity<MessageResponse> deleteHumanitarianAid(@PathVariable("humanitarianAidId") Long humanitarianAidId, Principal principal){
        needService.deleteNeed(humanitarianAidId,principal);
        return new ResponseEntity<>(new MessageResponse("Humanitarian Aid deleted successfully"), HttpStatus.OK);
    }


    @PostMapping("/create/psychologicalSupport")
    private ResponseEntity<MessageResponse> createPsychologicalSupport(@RequestBody PsychologicalSupportDto psychologicalSupportDto, Principal principal){
        needService.createPsychologicalSupport(psychologicalSupportDto,principal);
        return new ResponseEntity<>(new MessageResponse("Psychological Support created successfully"), HttpStatus.CREATED);
    }
    @GetMapping("/get/psychologicalSupport")
    private ResponseEntity<List<PsychologicalSupportDto>> getPsychologicalSupports(){
        List<PsychologicalSupportDto> psychologicalSupportDtoList = needService.getAllPsychologicalSupportDto();
        return new ResponseEntity<>(psychologicalSupportDtoList,HttpStatus.OK);
    }
    @PostMapping("/update/psychologicalSupport")
    private ResponseEntity<UpdatePsychologicalSupportDto> updateActiveFundraising(@RequestBody UpdatePsychologicalSupportDto updatePsychologicalSupportDto){
        UpdatePsychologicalSupportDto updatedPsychologicalSupport = needService.updatePsychologicalSupport(updatePsychologicalSupportDto);
        return new ResponseEntity<>(updatedPsychologicalSupport,HttpStatus.OK);

    }
    @DeleteMapping("/delete/psychologicalSupport/{psychologicalSupportId}")
    private ResponseEntity<MessageResponse> deletePsychologicalSupport(@PathVariable("psychologicalSupportId") Long psychologicalSupportId, Principal principal){
        needService.deleteNeed(psychologicalSupportId,principal);
        return new ResponseEntity<>(new MessageResponse("Psychological Support deleted successfully"), HttpStatus.OK);
    }
}
