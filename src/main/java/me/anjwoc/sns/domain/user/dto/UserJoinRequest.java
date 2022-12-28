package me.anjwoc.sns.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.anjwoc.sns.domain.user.User;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String email;
    private String password;

    public static UserJoinRequest from(User user) {
        return new UserJoinRequest(
                user.getEmail(),
                user.getPassword()
        );
    }

}
