package kr.co.polycube.backendtest.service;

import jakarta.transaction.Transactional;
import kr.co.polycube.backendtest.dto.UserDTO;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User saveUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class); // modelMapper로 UserDTO를 User 엔터티로 변환
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(Long userId, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        // 업데이트할 필드가 있을 경우에만 엔터티를 업데이트한다.
        if (updatedUserDTO.getName() != null) {
            existingUser.setId(updatedUserDTO.getId());
            existingUser.setName(updatedUserDTO.getName());
        }

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser,UserDTO.class);
    }
}
