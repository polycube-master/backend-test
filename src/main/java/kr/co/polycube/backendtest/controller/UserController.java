package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.UserDTO;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 등록 API
    @PostMapping
    public ResponseEntity<Map<String,Long>> registerUser(@RequestBody UserDTO userDTO){
        User createdUser=userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", createdUser.getId()));
    }

    // 특정 사용자 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        try {
            UserDTO existingUser = userService.getUserById(id);
            return ResponseEntity.ok(existingUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // 특정 사용자 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO){
        try {
            UserDTO existingUser = userService.updateUser(id,updatedUserDTO);
            return ResponseEntity.ok(existingUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
