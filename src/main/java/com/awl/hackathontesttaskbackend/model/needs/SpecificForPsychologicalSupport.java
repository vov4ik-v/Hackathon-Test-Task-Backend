package com.awl.hackathontesttaskbackend.model.needs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
//психологічна підтримка: піп, фото, коротка інфа, стан активності, замовити дзвінок

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SpecificForPsychologicalSupport {
    private String firstName;
    private String lastName;
}
