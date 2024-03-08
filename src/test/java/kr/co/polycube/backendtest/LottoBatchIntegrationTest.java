package kr.co.polycube.backendtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBatchTest
@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
public class LottoBatchIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testLottoJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        ExitStatus exitStatus = jobExecution.getExitStatus();
        if (!exitStatus.equals(ExitStatus.COMPLETED)) {
            throw new AssertionError("Batch 실행이 완료되지 않았습니다.");
        }
    }
}
