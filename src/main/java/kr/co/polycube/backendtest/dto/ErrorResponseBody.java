package kr.co.polycube.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 오류 응답을 보내는 reason 필드 dto
@Getter
@AllArgsConstructor
public class ErrorResponseBody {
    private String reason;
}
