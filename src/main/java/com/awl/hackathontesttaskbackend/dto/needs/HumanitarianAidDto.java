package com.awl.hackathontesttaskbackend.dto.needs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HumanitarianAidDto {
    private String imageUrl;
    private String description;
    private String needName;
    private String city;
}
