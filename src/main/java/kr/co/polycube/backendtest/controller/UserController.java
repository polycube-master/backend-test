package kr.co.polycube.backendtest.controller;

import jakarta.validation.Valid;
import kr.co.polycube.backendtest.dto.UserDto;
import kr.co.polycube.backendtest.dto.UserdetailDto;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> registerUser(@Valid @RequestBody UserDto userDto){
        User registeredUser = userService.registerUser(userDto);
        Map<String, Long> response = Map.of("id", registeredUser.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserdetailDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        UserdetailDto userdetailDto = new UserdetailDto(user.getId(), user.getName());
        return ResponseEntity.ok(userdetailDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserdetailDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        UserdetailDto updatedUserDto = new UserdetailDto(updatedUser.getId(), updatedUser.getName());
        return ResponseEntity.ok(updatedUserDto);
    }
}
