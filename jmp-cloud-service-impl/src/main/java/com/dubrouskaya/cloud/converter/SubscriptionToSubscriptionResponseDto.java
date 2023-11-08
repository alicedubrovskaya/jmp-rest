package com.dubrouskaya.cloud.converter;

import com.dubrouskaya.jmpdto.Subscription;
import com.dubrouskaya.jmpdto.dto.response.SubscriptionResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionToSubscriptionResponseDto implements Converter<Subscription, SubscriptionResponseDto> {
    @Override
    public SubscriptionResponseDto convert(Subscription subscription) {
        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .userId(subscription.getUser().getId())
                .startDate(subscription.getStartDate().toString())
                .build();
    }
}
