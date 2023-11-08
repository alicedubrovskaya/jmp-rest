package com.dubrouskaya.cloud.converter;

import com.dubrouskaya.jmpdto.User;
import com.dubrouskaya.jmpdto.dto.request.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {
    @Override
    public User convert(UserRequestDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate birthday = LocalDate.parse(dto.getBirthday(), formatter);
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .birthday(birthday)
                .build();
    }
}
