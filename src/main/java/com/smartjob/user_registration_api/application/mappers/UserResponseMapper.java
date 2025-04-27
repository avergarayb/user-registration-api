package com.smartjob.user_registration_api.application.mappers;

import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.domain.model.User;
import org.springframework.stereotype.Component;

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
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());

        return response;
    }

}
