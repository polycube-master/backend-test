package kr.co.polycube.backendtest;

import kr.co.polycube.backendtest.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendTestApplication.class)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 사용자_등록_API_통합테스트() {
        UserDto requestDto = new UserDto(null, "새 유저");

        ResponseEntity<UserDto> response = restTemplate.postForEntity("http://localhost:" + port + "/users", requestDto, UserDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(requestDto.getName());
    }

    @Test
    public void 사용자_조회_API_통합테스트() {
        UserDto newUser = restTemplate.postForObject("http://localhost:" + port + "/users", new UserDto(null, "조회용 유저"), UserDto.class);
        assert newUser != null;

        ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:" + port + "/users/" + newUser.getId(), UserDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(newUser.getId());
        assertThat(response.getBody().getName()).isEqualTo(newUser.getName());
    }

    @Test
    public void 사용자_수정_API_통합테스트() {
        UserDto newUser = restTemplate.postForObject("http://localhost:" + port + "/users", new UserDto(null, "수정 전 유저"), UserDto.class);
        assert newUser != null;

        UserDto updatedDto = new UserDto(newUser.getId(), "수정된 유저");

        restTemplate.put("http://localhost:" + port + "/users/" + newUser.getId(), updatedDto);

        ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:" + port + "/users/" + newUser.getId(), UserDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(newUser.getId());
        assertThat(response.getBody().getName()).isEqualTo(updatedDto.getName());
    }
}
