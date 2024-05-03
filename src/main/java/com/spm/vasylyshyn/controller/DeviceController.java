package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.payload.MessageResponse;
import com.spm.vasylyshyn.request.NeededDataForSendMeasurementsRequest;
import com.spm.vasylyshyn.request.RegisterDeviceRequest;
import com.spm.vasylyshyn.request.StatisticSettingsRequest;
import com.spm.vasylyshyn.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/device/")
// TODO: Update all response from Device to DeviceDTO
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("getDeviceByserialNumber/{serialNumber}")
    public ResponseEntity<Device> getDeviceByNumber(@PathVariable("serialNumber") Long serialNumber){
        Device device = deviceService.getDeviceBySerialNumber(serialNumber);
        return new ResponseEntity<>(device,HttpStatus.OK);

    }


//    @PostMapping("updateDevice/{serialNumber}")
//    public ResponseEntity<Device> updateDevice(@PathVariable("serialNumber") Long serialNumber, @RequestBody UpdateDeviceRequest updateDeviceRequest){
//        Device updatedDevice = deviceService.updateDevice(serialNumber,updateDeviceRequest);
//        return new ResponseEntity<>(updatedDevice,HttpStatus.OK);
//    }


    @PostMapping("calibrateDevice/{serialNumber}")
    public ResponseEntity<MessageResponse> calibrateDevice(@PathVariable("serialNumber") Long serialNumber){
        deviceService.calibrateDevice(serialNumber);
        return new ResponseEntity<>(new MessageResponse("Device calibrated successfully"),HttpStatus.CREATED);
    }
    @PostMapping("setNeededDataForSendMeasurements/{serialNumber}")
    public ResponseEntity<MessageResponse> setNeededDataForSendMeasurements(@PathVariable("serialNumber") Long serialNumber, @RequestBody NeededDataForSendMeasurementsRequest neededDataForSendMeasurementsRequest){
        deviceService.setNeededDataForSendMeasurementsRequest(serialNumber,neededDataForSendMeasurementsRequest);
        return new ResponseEntity<>(new MessageResponse("Needed data added successfully"),HttpStatus.CREATED);
    }
    @PostMapping("setStatisticSettings/{serialNumber}")
    public ResponseEntity<MessageResponse> setStatisticSettings(@PathVariable("serialNumber") Long serialNumber, @RequestBody StatisticSettingsRequest statisticSettingsRequest){
        deviceService.setStatisticSettings(serialNumber,statisticSettingsRequest);
        return new ResponseEntity<>(new MessageResponse("Statistic settings added successfully"),HttpStatus.CREATED);
    }



    @PostMapping("register")
    public ResponseEntity<Device> registerDevice(@RequestBody RegisterDeviceRequest request, Principal principal){
        Device createdDevice = deviceService.registerDevice(request,principal);
        return new ResponseEntity<>(createdDevice,HttpStatus.CREATED);
    }

}
