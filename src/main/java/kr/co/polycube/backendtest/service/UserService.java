package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.dto.UserDto;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.exception.UserNotFoundException;
import kr.co.polycube.backendtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, UserDto userDto) {
        User existingUser = getUser(id);
        existingUser.setName(userDto.getName());
        return userRepository.save(existingUser);
    }
}
