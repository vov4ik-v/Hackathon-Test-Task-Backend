package com.awl.hackathontesttaskbackend.dto.needs.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHumanitarianAidDto {
    private Long id;
    private String imageUrl;
    private String description;
    private String needName;
    private String city;
    private Boolean stateOfNeed;
}
