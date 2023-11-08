package com.dubrouskaya.cloud.service;

import com.dubrouskaya.cloud.converter.UserRequestDtoToUserConverter;
import com.dubrouskaya.cloud.converter.UserToUserResponseDtoConverter;
import com.dubrouskaya.cloud.repository.UserRepository;
import com.dubrouskaya.jmpdto.User;
import com.dubrouskaya.jmpdto.dto.request.UserRequestDto;
import com.dubrouskaya.jmpdto.dto.response.UserResponseDto;
import com.dubrouskaya.jmpserviceapi.UserService;
import com.dubrouskaya.jmpservicerest.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserRequestDtoToUserConverter toUserConverter;
    private final UserToUserResponseDtoConverter toResponseDtoConverter;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserRequestDtoToUserConverter toUserConverter,
                           UserToUserResponseDtoConverter toResponseDtoConverter) {
        this.repository = repository;
        this.toUserConverter = toUserConverter;
        this.toResponseDtoConverter = toResponseDtoConverter;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = toUserConverter.convert(userRequestDto);
        user = repository.save(user);
        return toResponseDtoConverter.convert(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        User user = toUserConverter.convert(userRequestDto);
        if (repository.findById(user.getId()).isPresent()) {
            user = repository.save(user);
        } else {
            throw new UserNotFoundException(user.getId());
        }
        return toResponseDtoConverter.convert(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return toResponseDtoConverter.convert(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return repository.findAll().stream()
                .map(toResponseDtoConverter::convert)
                .toList();
    }
}
