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

    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(mapPhonesToEntities(user.getPhones()))
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .active(user.isActive())
                .build();
    }

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

    private List<PhoneEntity> mapPhonesToEntities(List<Phone> phones) {
        if (phones == null) {
            return null;
        }

        return phones.stream()
                .map(this::mapPhoneToEntity)
                .collect(Collectors.toList());
    }

    private PhoneEntity mapPhoneToEntity(Phone phone) {
        return PhoneEntity.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
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
