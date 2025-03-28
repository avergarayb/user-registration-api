package com.smartjob.user_registration_api.application.mappers;

import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.domain.model.Phone;
import com.smartjob.user_registration_api.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseMapper {

    /**
     * Mapeaa un objeto User a un objeto UserResponse.
     * @param user objeto User que se va a convertir
     * @return UserResponse objeto que representa la respuesta del usuario
     */
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhones(mapPhonesToResponses(user.getPhones()));
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());

        return response;
    }

    private List<UserResponse.PhoneResponse> mapPhonesToResponses(List<Phone> phones) {
        if (phones == null) {
            return null;
        }

        return phones.stream()
                .map(this::mapPhoneToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse.PhoneResponse mapPhoneToResponse(Phone phone) {
        UserResponse.PhoneResponse response = new UserResponse.PhoneResponse();
        response.setNumber(phone.getNumber());
        response.setCityCode(phone.getCityCode());
        response.setCountryCode(phone.getCountryCode());
        return response;
    }
}
