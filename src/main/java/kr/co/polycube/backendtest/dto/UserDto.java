package kr.co.polycube.backendtest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "Name을 써야 합니다!")
    private String name;
}
