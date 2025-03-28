package com.smartjob.user_registration_api.infrastructure.mappers;

import com.smartjob.user_registration_api.domain.model.Phone;
import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.infrastructure.entities.PhoneEntity;
import com.smartjob.user_registration_api.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /**
     * Mapea un objeto User del dominio a un objeto UserEntity de la base de datos.
     * @param user objeto que representa al usuario en el dominio
     * @return UserEntity objeto que representa al usuario en la base de datos
     */
    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .active(user.isActive())
                .build();

        // Mapear teléfonos y establecer la relación bidireccional
        if (user.getPhones() != null) {
            List<PhoneEntity> phoneEntities = mapPhonesToEntities(user.getPhones(), userEntity);
            userEntity.setPhones(phoneEntities);
        }

        return userEntity;
    }

    /**
     * Convierte un objeto UserEntity de la base de datos a un objeto User del dominio.
     * @param entity objeto que representa al usuario en la base de datos
     * @return User objeto que representa al usuario en el dominio
     */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phones(mapPhoneEntitiesToDomain(entity.getPhones()))
                .created(entity.getCreated())
                .modified(entity.getModified())
                .lastLogin(entity.getLastLogin())
                .token(entity.getToken())
                .active(entity.isActive())
                .build();
    }

    private List<PhoneEntity> mapPhonesToEntities(List<Phone> phones, UserEntity userEntity) {
        if (phones == null) {
            return null;
        }

        return phones.stream()
                .map(phone -> mapPhoneToEntity(phone, userEntity))
                .collect(Collectors.toList());
    }

    private PhoneEntity mapPhoneToEntity(Phone phone, UserEntity userEntity) {
        return PhoneEntity.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .user(userEntity)
                .build();
    }

    private List<Phone> mapPhoneEntitiesToDomain(List<PhoneEntity> phoneEntities) {
        if (phoneEntities == null) {
            return null;
        }

        return phoneEntities.stream()
                .map(this::mapPhoneEntityToDomain)
                .collect(Collectors.toList());
    }

    private Phone mapPhoneEntityToDomain(PhoneEntity phoneEntity) {
        return Phone.builder()
                .number(phoneEntity.getNumber())
                .cityCode(phoneEntity.getCityCode())
                .countryCode(phoneEntity.getCountryCode())
                .build();
    }
}
