package me.anjwoc.sns.domain.user;

import lombok.RequiredArgsConstructor;
import me.anjwoc.sns.common.exception.APIErrorCode;
import me.anjwoc.sns.common.exception.APIException;
import me.anjwoc.sns.domain.user.dto.UserDto;
import me.anjwoc.sns.domain.user.dto.UserJoinRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto join(UserJoinRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent((it) -> {
            throw new APIException(APIErrorCode.DUPLICATED_USER_EMAIL, "email", request.getEmail());
        });

        User user = userRepository.save(User.of(request.getEmail(), request.getPassword()));
        return UserDto.from(user);
    }
}
