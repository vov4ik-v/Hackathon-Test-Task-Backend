package com.spm.vasylyshyn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TelegramSentData {
    private Long telegramChatId;
    private String displayName;
    private String icon;
}
