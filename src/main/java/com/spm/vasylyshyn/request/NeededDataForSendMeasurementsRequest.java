package com.spm.vasylyshyn.request;

import com.spm.vasylyshyn.enums.Regularity;
import com.spm.vasylyshyn.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NeededDataForSendMeasurementsRequest {
    private Long telegramChatId;
    private String displayName;
    private String icon;
    private Address address;
    private String phoneNumber;
    private Regularity regularity;
}
