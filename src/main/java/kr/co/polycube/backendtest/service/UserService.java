package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.dto.UserDto;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .build();
        user = userRepository.save(user);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        User savedUser = userRepository.save(user);
        return UserDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .build();
    }
}
