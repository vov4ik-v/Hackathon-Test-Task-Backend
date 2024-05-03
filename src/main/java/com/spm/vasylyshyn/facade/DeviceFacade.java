package com.spm.vasylyshyn.facade;


import com.spm.vasylyshyn.dto.device.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceFacade {

    public DeviceDto deviceToDeviceDTO(Device device){
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setMeasurements(device.getMeasurements());



        return deviceDto;
    }
}
