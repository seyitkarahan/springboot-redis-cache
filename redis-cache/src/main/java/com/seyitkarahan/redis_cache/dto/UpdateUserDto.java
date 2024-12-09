package com.seyitkarahan.redis_cache.dto;

import com.seyitkarahan.redis_cache.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private Long id;

    private String password;

    public User toEntity(UpdateUserDto updateUserDto) {
        return User.builder()
                .id(updateUserDto.getId())
                .password(updateUserDto.getPassword())
                .build();
    }
}
