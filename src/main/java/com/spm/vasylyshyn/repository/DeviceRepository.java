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
}
