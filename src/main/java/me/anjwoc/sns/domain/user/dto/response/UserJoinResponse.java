package me.anjwoc.sns.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.anjwoc.sns.domain.user.dto.UserDto;

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
