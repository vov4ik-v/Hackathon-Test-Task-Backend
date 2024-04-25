package com.awl.hackathontesttaskbackend.dto.needs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PsychologicalSupportDto {
    private String imageUrl;
    private String description;
    private String firstName;
    private String lastName;
}
