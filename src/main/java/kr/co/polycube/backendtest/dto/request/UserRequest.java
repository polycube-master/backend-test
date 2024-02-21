package kr.co.polycube.backendtest.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.polycube.backendtest.domain.User;

public record UserRequest(
        @NotBlank(message = "null 또는 공백이 아니어야 합니다.")
        String userName) {

        public User toEntity(UserRequest userRequest) {
                return User.of(userRequest.userName());
        }
}
