package com.spm.vasylyshyn.controller;

import com.spm.vasylyshyn.response.ApiResponse;
import com.spm.vasylyshyn.dto.MeasurementDto;
import com.spm.vasylyshyn.model.Measurement;
import com.spm.vasylyshyn.service.MeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/display_count/")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("getMeasurementsByDeviceNumber/{serialNumber}")
    public ResponseEntity<List<MeasurementDto>> getMeasurementsByDeviceNumber(@PathVariable("serialNumber") Long serialNumber){
        List<MeasurementDto> measurementsDto = measurementService.getMeasurementsDtoByDeviceNumber(serialNumber);
        return new ResponseEntity<>(measurementsDto,HttpStatus.OK);
    }


    @GetMapping("getLastCollectedMeasurement/{serialNumber}")
    public ResponseEntity<MeasurementDto> getLastCollectedMeasurement(@PathVariable("serialNumber") Long serialNumber){

        return new ResponseEntity<>(measurementService.getLastCollectedMeasurement(serialNumber),HttpStatus.OK);
    }

    @GetMapping("getAllMeasurements")
    public ResponseEntity<List<Measurement>> getAllDevices(){
        List<Measurement> measurements = measurementService.getAllDisplays();
        return new ResponseEntity<>(measurements,HttpStatus.OK);

    }

    @PostMapping("addmeasurement")
    public ResponseEntity<ApiResponse> addmeasurement(@RequestBody MeasurementDto measurementDto, @RequestParam("device_id") Long deviceId){
        measurementService.addMeasurement(measurementDto,deviceId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @GetMapping("getInRange/{serialNumber}")
    public ResponseEntity<List<Measurement>> getMeasurementInDateRange(
        @PathVariable("serialNumber") Long serialNumber,
        @RequestParam("startRange") LocalDateTime startRange,
        @RequestParam("endRange")LocalDateTime endRange
    ){
        List<Measurement> allMeasurementsInRange = measurementService.findAllmeasurementsInRange(serialNumber, startRange, endRange);
        return new ResponseEntity<>(allMeasurementsInRange,HttpStatus.OK);
    }
}
