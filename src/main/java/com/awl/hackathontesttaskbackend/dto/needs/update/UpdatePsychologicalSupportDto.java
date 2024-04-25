package com.awl.hackathontesttaskbackend.dto.needs.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePsychologicalSupportDto {
    private Long id;
    private String imageUrl;
    private String description;
    private String firstName;
    private String lastName;
    private Boolean stateOfNeed;
}
