package com.smartjob.user_registration_api.application.mappers;

import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.domain.model.Phone;
import com.smartjob.user_registration_api.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRequestMapper {

    public User toDomain(UserRequest request) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phones(mapPhoneRequests(request.getPhones()))
                .build();
    }

    private List<Phone> mapPhoneRequests(List<UserRequest.PhoneRequest> phoneRequests) {
        if (phoneRequests == null) {
            return null;
        }

        return phoneRequests.stream()
                .map(this::mapPhoneRequest)
                .collect(Collectors.toList());
    }

    private Phone mapPhoneRequest(UserRequest.PhoneRequest phoneRequest) {
        return Phone.builder()
                .number(phoneRequest.getNumber())
                .cityCode(phoneRequest.getCityCode())
                .countryCode(phoneRequest.getCountryCode())
                .build();
    }
}
