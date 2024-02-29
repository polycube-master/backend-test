package kr.co.polycube.backendtest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.dto.UserDTO;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpecialCharacterFilterTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private UserService userService;


    @Test
    public void testSpecialCharacterInURL() {
        // 요청받는 UserDTO 객체 임의 설정
        UserDTO userDTO=new UserDTO();
        userDTO.setName("test");

        // 생성된 User 객체 임의 생성
        User createUser=new User(1L,"test");

        // UserService 의 saveUser 가 호출될 때 Mockito 를 사용하여 createUser 객체가 반환됨
        Mockito.when(userService.saveUser(Mockito.any(UserDTO.class))).thenReturn(createUser);

        ResponseEntity<String> response = restTemplate.exchange("/users/1?name=test!!", HttpMethod.GET, null, String.class);
        // 응답의 상태 코드 및 본문 확인
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
    @Test
    public void testNoSpecialCharacterInURL() {
        ResponseEntity<Void> response = restTemplate.getForEntity("/users/1?name=test", Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}