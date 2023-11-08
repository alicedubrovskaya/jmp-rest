package com.dubrouskaya.jmpdto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {

    private Long id;
    private Long userId;
    private String startDate;
}
