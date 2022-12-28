package me.anjwoc.sns.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {
    private Long id;
    private String email;

    public static UserJoinResponse from(UserDto userDto) {
        return new UserJoinResponse(
                userDto.getId(),
                userDto.getEmail()
        );
    }
}
