package kr.co.polycube.backendtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;

    public UserDTO() {
        // 기본 생성자
    }

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}