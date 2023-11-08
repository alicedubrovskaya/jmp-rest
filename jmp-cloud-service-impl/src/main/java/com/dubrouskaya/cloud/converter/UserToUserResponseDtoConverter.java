package com.dubrouskaya.cloud.converter;

import com.dubrouskaya.jmpdto.User;
import com.dubrouskaya.jmpdto.dto.response.UserResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {
    @Override
    public UserResponseDto convert(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthday(user.getBirthday().toString())
                .build();
    }
}
