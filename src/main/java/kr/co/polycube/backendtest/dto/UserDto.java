package kr.co.polycube.backendtest.dto;

import jakarta.persistence.Access;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotNull(message = "name은 필수 항목입니다.")
    private String name;
}
