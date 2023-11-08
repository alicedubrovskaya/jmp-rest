package com.dubrouskaya.jmpservicerest.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(Long id) {
        super(String.format("Subscription is not found with id : '%s'", id));
    }
}
