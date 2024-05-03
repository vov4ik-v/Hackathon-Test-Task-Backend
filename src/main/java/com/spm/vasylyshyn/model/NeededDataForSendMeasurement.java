package com.spm.vasylyshyn.model;


import com.spm.vasylyshyn.enums.Regularity;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Embeddable
public class NeededDataForSendMeasurement {

    @Embedded
    private TelegramSentData telegramSentData;

    @Embedded
    private PersonalAccount personalAccount;

    @Enumerated(EnumType.STRING)
    private Regularity regularity;

}
