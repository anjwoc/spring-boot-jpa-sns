package me.anjwoc.sns.domain.user;

import lombok.RequiredArgsConstructor;
import me.anjwoc.sns.common.exception.APIErrorCode;
import me.anjwoc.sns.common.exception.APIException;
import me.anjwoc.sns.common.utils.JwtTokenUtils;
import me.anjwoc.sns.domain.user.dto.UserDto;
import me.anjwoc.sns.domain.user.dto.request.UserJoinRequest;
import me.anjwoc.sns.domain.user.dto.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.token.expiredAt}")
    private Long expiredAt;

    @Transactional
    public UserDto join(UserJoinRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent((it) -> {
            throw new APIException(APIErrorCode.DUPLICATED_USER_EMAIL, "email", request.getEmail());
        });

        User user = userRepository.save(User.of(request.getEmail(), passwordEncoder.encode(request.getPassword())));
        return UserDto.from(user);
    }

    public String login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new APIException(APIErrorCode.USER_NOT_FOUND, "email", request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new APIException(APIErrorCode.INVALID_PASSWORD);
        }

        return JwtTokenUtils.generateAccessToken(request.getEmail(), secretKey, expiredAt);

    }
}
