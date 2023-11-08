package com.dubrouskaya.jmpservicerest.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User is not found with id : '%s'", id));
    }
}
