package com.dubrouskaya.cloud.service;

import com.dubrouskaya.cloud.converter.SubscriptionRequestDtoToSubscription;
import com.dubrouskaya.cloud.converter.SubscriptionToSubscriptionResponseDto;
import com.dubrouskaya.cloud.repository.SubscriptionRepository;
import com.dubrouskaya.cloud.repository.UserRepository;
import com.dubrouskaya.jmpdto.Subscription;
import com.dubrouskaya.jmpdto.User;
import com.dubrouskaya.jmpdto.dto.request.SubscriptionRequestDto;
import com.dubrouskaya.jmpdto.dto.response.SubscriptionResponseDto;
import com.dubrouskaya.jmpserviceapi.SubscriptionService;
import com.dubrouskaya.jmpservicerest.exception.SubscriptionNotFoundException;
import com.dubrouskaya.jmpservicerest.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final UserRepository userRepository;

    private final SubscriptionRequestDtoToSubscription toSubscriptionConverter;

    private final SubscriptionToSubscriptionResponseDto toResponseDtoConverter;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository,
                                   UserRepository userRepository,
                                   SubscriptionRequestDtoToSubscription toSubscriptionConverter,
                                   SubscriptionToSubscriptionResponseDto toResponseDtoConverter) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.toSubscriptionConverter = toSubscriptionConverter;
        this.toResponseDtoConverter = toResponseDtoConverter;
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Long userId = subscriptionRequestDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Subscription subscription = toSubscriptionConverter.convert(subscriptionRequestDto);
        subscription.setUser(user);
        subscription = repository.save(subscription);

        return toResponseDtoConverter.convert(subscription);
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Long userId = subscriptionRequestDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Subscription subscription = toSubscriptionConverter.convert(subscriptionRequestDto);

        if (repository.findById(subscription.getId()).isPresent()) {
            subscription.setUser(user);
            subscription = repository.save(subscription);
        } else {
            throw new UserNotFoundException(subscription.getId());
        }
        return toResponseDtoConverter.convert(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new SubscriptionNotFoundException(id);
        }
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long id) {
        Subscription subscription = repository.findById(id).orElseThrow(() -> new SubscriptionNotFoundException(id));
        return toResponseDtoConverter.convert(subscription);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return repository.findAll().stream()
                .map(toResponseDtoConverter::convert)
                .toList();
    }
}
