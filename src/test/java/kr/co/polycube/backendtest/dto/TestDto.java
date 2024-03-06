package kr.co.polycube.backendtest.dto;

import jakarta.validation.constraints.NotNull;

public class TestDto {

    @NotNull(message = "name은 필수 항목입니다.")
    private String name;

}
