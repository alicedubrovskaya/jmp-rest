package com.dubrouskaya.jmpserviceapi;

import com.dubrouskaya.jmpdto.dto.request.SubscriptionRequestDto;
import com.dubrouskaya.jmpdto.dto.response.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long id);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscriptions();
}
