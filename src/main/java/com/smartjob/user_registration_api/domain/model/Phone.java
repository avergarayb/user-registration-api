package com.smartjob.user_registration_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
}
