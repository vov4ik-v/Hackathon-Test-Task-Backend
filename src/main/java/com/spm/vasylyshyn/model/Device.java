package com.spm.vasylyshyn.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private Long serialNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isCalibrated;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy="device")
    private List<Measurement> measurements = new ArrayList<>();

    @Column(updatable = false)

    private LocalDateTime createdDate;

    @Embedded
    private StatisticSettings statisticSettings;

    @Embedded
    private NeededDataForSendMeasurement neededDataForSendMeasurement;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }






}
