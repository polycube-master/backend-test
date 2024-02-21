package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.Repository.UserRepository;
import kr.co.polycube.backendtest.domain.User;
import kr.co.polycube.backendtest.dto.request.UserRequest;
import kr.co.polycube.backendtest.dto.response.UserResponse;
import kr.co.polycube.backendtest.exception.ExHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest dto) {

        User user = dto.toEntity(dto);
        userRepository.save(user);

        return UserResponse.from(user);
    }

    public UserResponse readUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExHandler.ResourceNotFoundException("해당 ID 유저가 존재하지 않습니다."));

        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExHandler.ResourceNotFoundException("해당 ID 유저가 존재하지 않습니다."));

        String userName = dto.userName();
        user.updateUserName(userName);
        userRepository.save(user);

        return UserResponse.from(user);
    }
}
