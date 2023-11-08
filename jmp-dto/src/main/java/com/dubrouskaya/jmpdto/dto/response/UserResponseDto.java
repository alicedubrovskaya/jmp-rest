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
public class UserResponseDto extends RepresentationModel<UserResponseDto> {
    Long id;
    String name;
    String surname;
    String birthday;
}
