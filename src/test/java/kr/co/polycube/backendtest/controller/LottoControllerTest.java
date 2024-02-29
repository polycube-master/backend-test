package kr.co.polycube.backendtest.controller;


import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LottoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGenerateLottoNumbers_thenResponseIsCorrect(){
        ResponseEntity<Map> responseEntity = restTemplate.exchange("/lottos", HttpMethod.POST, null, Map.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, List<Integer>> responseBody=responseEntity.getBody();
        assertNotNull(responseBody);
        assertTrue(responseBody.containsKey("numbers"));
        List<Integer> numbers=responseBody.get("numbers");
        assertEquals(6,numbers.size());
        for(int number :numbers){
            assertTrue(number>=1 && number <=45); // 로또 번호는 1부터 45 사이의 값이어야 한다
        }

    }
}
