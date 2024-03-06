package kr.co.polycube.backendtest;

import kr.co.polycube.backendtest.config.BatchConfig;
import kr.co.polycube.backendtest.config.BatchRun;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BackendTestApplicationTests.class)
@EnableBatchProcessing
@SpringBootApplication
@ContextConfiguration(classes = {LottoBatchIntegrationTest.BatchTestConfig.class, BatchConfig.class, BatchRun.class})
public class LottoBatchIntegrationTest {

    @Autowired
    private BatchRun batchRun;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private WinnerRepository winnerRepository;

//    @BeforeEach
//    void setupTestData() {
//        Lotto testLotto = Lotto.builder()
//                .number1(1).number2(2).number3(3).number4(4).number5(5).number6(6)
//                .build();
//        lottoRepository.save(testLotto);
//    }
//
//    @Test
//    void 로또_당첨_배치_테스트() throws Exception {
//        batchRun.runLottoWinnerJob();
//
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
//
//        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
//
//        // 데이터베이스에서 당첨자 정보를 조회하여 검증
//        List<Winner> winners = winnerRepository.findAll();
//        assertThat(winners).isNotEmpty();
//        assertThat(winners.get(0).getRank()).isEqualTo(1); // 예제 당첨 번호와 완벽히 일치한다고 가정
//    }

    @Test
    @Sql("/test-data.sql") // 테스트 데이터 로드
    void 로또_당첨_배치_테스트() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);

        // 데이터베이스에서 당첨자 정보 조회
        List<Winner> winners = winnerRepository.findAll();

        // 당첨자 정보가 올바르게 저장되었는지 확인
        assertThat(winners).isNotEmpty();

        // 당첨 번호와 일치하는 번호의 개수에 따른 등수를 검증
        // 1등 당첨자 수 검증
        long firstPrizeWinners = winners.stream()
                .filter(winner -> winner.getRank() == 1)
                .count();
        assertThat(firstPrizeWinners).isGreaterThan(0);

        // 로또 당첨 번호를 임의로 LottoService에 설정 해두었으며, 테스트에서는 1등만 검증하는 로직을 작성하였습니다.
        // 추가적으로 로또 당첨 번호 개수를 반복문을 통해서 돌리면서 2등, 3등, 4등, 5등을 검증할 수 있습니다.
    }

    @Configuration
    static class BatchTestConfig {
        @Autowired
        private JobLauncher jobLauncher;

        @Autowired
        private Job lottoWinnerJob;

        @Autowired
        private JobRepository jobRepository;

        @Bean
        public JobLauncherTestUtils jobLauncherTestUtils() {
            JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
            jobLauncherTestUtils.setJobLauncher(jobLauncher);
            jobLauncherTestUtils.setJob(lottoWinnerJob);
            jobLauncherTestUtils.setJobRepository(jobRepository);
            return jobLauncherTestUtils;
        }
    }

}
