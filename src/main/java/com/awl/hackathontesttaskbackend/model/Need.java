package com.awl.hackathontesttaskbackend.model;


import com.awl.hackathontesttaskbackend.enums.NeedType;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForActiveFundraisings;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForHumanitarianAid;
import com.awl.hackathontesttaskbackend.model.needs.SpecificForPsychologicalSupport;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"announcementMaker","usersWhoSelected"})

public class Need {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String description;

    @Embedded
    private SpecificForActiveFundraisings specificForActiveFundraisings;
    @Embedded
    private SpecificForHumanitarianAid specificForHumanitarianAid;
    @Embedded
    private SpecificForPsychologicalSupport specificForPsychologicalSupport;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NeedType needType;

    private Boolean stateOfNeed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_model_id", referencedColumnName = "id")
    private ImageModel imageModel;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User announcementMaker;

    @ManyToMany(mappedBy = "selectedNeeded")
    @JsonIgnore
    private List<User> usersWhoSelected;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }


}
