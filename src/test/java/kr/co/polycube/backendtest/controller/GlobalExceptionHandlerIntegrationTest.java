package kr.co.polycube.backendtest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;


    // 유효하지 않은 요청일 때 Bad Request 가 발생하고 에러 응답을 예상하는 테스트 코드
    @Test
    public void givenInvalidRequest_whenBadRequest_thenExpectErrorResponse() throws Exception {
        String invalidRequest = "Invalid Request";

        mockMvc.perform( // MockMvc 객체를 사용하여 POST 요청을 실행한다.
                MockMvcRequestBuilders.post("/api/someendpoint") // POST 요청을 생성하고
                        .content(invalidRequest) // 요청의 본문을 설정하고
                        .contentType(MediaType.APPLICATION_JSON)) // 요청의 컨텐츠 타입을 JSON 으로 설정한다.
                .andExpect(status().isBadRequest()) // 예상된 응답의 상태가 코드가 BadRequest(400)인지 확인한다.
                .andExpect(jsonPath("$.reason").exists());
                // 예상된 응답의 JSON 본문에서 "reason" 필드가 존재하는지 확인한다
                // 따라서 응답이 {"reason": ...} 형태로 되어 있는지 확인한다.
    }

    // 존재하는 않는 엔드포인트 요청일 때 NotFound 가 발생하고 에러 응답을 예상하는 테스트 코드
    @Test
    public void givenNonexistentEndpoint_whenNotFound_thenExpectErrorResponse() throws Exception{
        mockMvc.perform( // MockMvc 객체를 사용해여 GET 요청을 실행한다.
                MockMvcRequestBuilders.get("/api/nonexistentendpoint")) // GET 요청을 생성한다. "/api/nonexistentendpoint" 존재하지 않는 임의의 엔드포인트이다.
                .andExpect(status().isNotFound()) // 예상된 응답의 상태코드가 NotFound(404)인지 확인한다.
                .andExpect(jsonPath("$.reason").exists());
                // 예상된 응답의 JSON 본문에서 "reason" 필드가 존재하는지 확인한다.
                // 따라서 응답이 {"reason": ...} 형태로 되어 있는지 확인한다.
    }

}
