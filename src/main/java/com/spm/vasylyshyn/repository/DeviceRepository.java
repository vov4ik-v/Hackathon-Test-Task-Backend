package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.dto.device.DeviceDto;
import com.spm.vasylyshyn.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device> findDeviceBySerialNumber(Long serialNumber);


//    @Query("select new com.spm.vasylyshyn.dto.DeviceDto(d.numberOfDevice,d.cantoraName,d.Measurements,d.address,d.frequency,d.counterType,d.price,d.createdDate) from Device as d")
//    Optional<DeviceDto> findDeviceDtoByNumberOfDevice(Long deviceNumber);
}
