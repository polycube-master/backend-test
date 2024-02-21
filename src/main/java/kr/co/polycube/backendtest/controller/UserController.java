package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.request.UserRequest;
import kr.co.polycube.backendtest.dto.response.UserResponse;
import kr.co.polycube.backendtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequest dto) {

        UserResponse userResponse = userService.createUser(dto);

        return ResponseEntity.ok().body(userResponse.id());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> readUser(@PathVariable Long id) {

        UserResponse user = userService.readUser(id);

        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest dto) {

        UserResponse user = userService.updateUser(id, dto);

        return ResponseEntity.ok().body(user);
    }
}
