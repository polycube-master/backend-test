package kr.co.polycube.backendtest.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration"
})
public class LottoWinnerBatchTest {

    @Autowired
    private LottoWinnerBatch lottoWinnerBatch;

    @Test
    public void testCheckWinners(){
        // 배치가 실행되는지 테스트하는 코드 추가
        lottoWinnerBatch.checkWinners();
    }
}
