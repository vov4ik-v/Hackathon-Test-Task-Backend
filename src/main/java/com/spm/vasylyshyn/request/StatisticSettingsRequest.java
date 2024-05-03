package com.spm.vasylyshyn.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticSettingsRequest {
    private Long statisticFrequency;
    private Float tariff;
}
