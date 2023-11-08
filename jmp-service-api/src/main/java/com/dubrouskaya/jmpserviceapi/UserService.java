package com.dubrouskaya.jmpserviceapi;

import com.dubrouskaya.jmpdto.dto.request.UserRequestDto;
import com.dubrouskaya.jmpdto.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();
}
