package com.dubrouskaya.cloud.converter;

import com.dubrouskaya.jmpdto.Subscription;
import com.dubrouskaya.jmpdto.User;
import com.dubrouskaya.jmpdto.dto.request.SubscriptionRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionRequestDtoToSubscription implements Converter<SubscriptionRequestDto, Subscription> {
    @Override
    public Subscription convert(SubscriptionRequestDto dto) {
        final User user = User.builder().id(dto.getUserId()).build();

        return Subscription.builder()
                .id(dto.getId())
                .user(user)
                .startDate(LocalDate.now())
                .build();
    }
}
