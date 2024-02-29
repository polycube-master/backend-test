package kr.co.polycube.backendtest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler=new GlobalExceptionHandler();

    // 유효하지 않은 요청일 때 Bad Request 가 발생하고 에러 응답을 예상하는 테스트 코드
    @Test
    public void handleIllegalArgumentException_ShouldReturnBadRequest(){
        //given
        IllegalArgumentException exception=new IllegalArgumentException("Invalid input");

        //when
        ResponseEntity<Map<String,String>> responseEntity=exceptionHandler.handleIllegalArgumentException(exception);

        //then
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertEquals("Invalid input", Objects.requireNonNull(responseEntity.getBody()).get("reason"));

    }

    // 존재하는 않는 엔드포인트 요청일 때 NotFound 가 발생하고 에러 응답을 예상하는 테스트 코드
    @Test
    public void  handleNoHandlerFoundException_ShouldReturnNotFound() {
        // given
        NoHandlerFoundException exception=new NoHandlerFoundException("GET", "/some/url", null);

        //when
        ResponseEntity<Map<String,String>> responseEntity=exceptionHandler.handleNoHandlerFoundException(exception);

        //then
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        assertEquals("No endpoint GET /some/url.", Objects.requireNonNull(responseEntity.getBody()).get("reason"));
    }

}
