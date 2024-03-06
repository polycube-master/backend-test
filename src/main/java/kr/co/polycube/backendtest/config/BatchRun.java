package kr.co.polycube.backendtest.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchRun {
	private static final Logger logger = LoggerFactory.getLogger(BatchRun.class);

	private final JobLauncher jobLauncher;

	private final Job lottoWinnerJob;

	@Scheduled(cron = "0 0 0 * * SUN")
	public void runLottoWinnerJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		// 시간 관련 라이브러리로 테스트 해야하지만, 일단 수동으로 동작 확인을 위해 log 사용
		logger.info("BatchRun 시작 : {}", System.currentTimeMillis());

		JobParameters params = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(lottoWinnerJob, params);

		logger.info("BatchRun 끝 : {}", System.currentTimeMillis());
	}
}
