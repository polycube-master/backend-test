package kr.co.polycube.backendtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendTestApplication.class)
public class LottoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 로또_만들기_API_통합_테스트() {
        ResponseEntity<Map> response = restTemplate.postForEntity("http://localhost:" + port + "/lottos", null, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).containsKey("numbers");

        // 로또 번호 검증은 복잡도가 높기 때문에 여기서는 응답에 "numbers" 키가 있는지
        // 그리고 6개의 숫자가 있는지만 확인합니다.
        // 실제 값에 대한 검증은 로직을 통한 검증이 필요합니다.
        List<Integer> numbers = (List<Integer>) response.getBody().get("numbers");
        assertThat(numbers).hasSize(6);
    }
}
