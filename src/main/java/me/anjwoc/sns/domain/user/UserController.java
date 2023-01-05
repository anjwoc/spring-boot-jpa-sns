package me.anjwoc.sns.domain.user;

import lombok.RequiredArgsConstructor;
import me.anjwoc.sns.common.response.BaseResponse;
import me.anjwoc.sns.domain.user.dto.request.UserJoinRequest;
import me.anjwoc.sns.domain.user.dto.request.UserLoginRequest;
import me.anjwoc.sns.domain.user.dto.response.UserJoinResponse;
import me.anjwoc.sns.domain.user.dto.response.UserLoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public BaseResponse<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        return BaseResponse.success(UserJoinResponse.from(userService.join(request)));
    }

    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request);
        return BaseResponse.success(new UserLoginResponse(token));
    }
}