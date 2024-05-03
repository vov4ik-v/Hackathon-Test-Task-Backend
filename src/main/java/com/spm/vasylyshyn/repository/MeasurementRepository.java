package com.spm.vasylyshyn.repository;

import com.spm.vasylyshyn.dto.MeasurementDto;
import com.spm.vasylyshyn.model.Device;
import com.spm.vasylyshyn.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Optional<List<Measurement>> findMeasurementsByDevice(Device device);

    @Query("select new com.spm.vasylyshyn.dto.MeasurementDto(m.id, m.measurement, m.isSubmitted, m.createdDate) from Measurement as m where m.id = max(m.id)")
    Optional<MeasurementDto> findDto(Device device);

//    TODO:Write a query statement
    List<Measurement> findMeasurementsInRangeDate(Device device,  LocalDateTime startRange,  LocalDateTime endRange);


    @Query("select new com.spm.vasylyshyn.dto.MeasurementDto(m.id, m.measurement, m.isSubmitted,  m.createdDate) from Measurement as m where m.device = :device")
    Optional<List<MeasurementDto>> findMeasurementsDtoByDevice(@Param("device") Device device);
}
