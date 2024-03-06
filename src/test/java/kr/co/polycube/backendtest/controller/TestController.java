package kr.co.polycube.backendtest.controller;

import jakarta.validation.Valid;
import kr.co.polycube.backendtest.dto.TestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/validate")
    public ResponseEntity<Object> testValidation(@RequestBody @Valid TestDto testDto) {
        return ResponseEntity.ok().build();
    }
}
