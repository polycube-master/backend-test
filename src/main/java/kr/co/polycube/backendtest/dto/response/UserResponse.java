package kr.co.polycube.backendtest.dto.response;

import kr.co.polycube.backendtest.domain.User;

public record UserResponse(
        Long id,
        String userName) {

    public static UserResponse of(
            Long id,
            String userName
    ) {
        return new UserResponse(id, userName);
    }

    public static UserResponse from(User user) {
        return UserResponse.of(
                user.getId(),
                user.getUserName());
    }
}
