package kr.co.polycube.backendtest.batch;

import jakarta.persistence.EntityManagerFactory;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import kr.co.polycube.backendtest.service.LottoService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;

@Configuration
public class LottoJobConfiguration {

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public ItemReader<Lotto> lottoItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<Lotto>()
                .name("lottoItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT l FROM Lotto l")
                .build();
    }

    @Bean
    public ItemProcessor<Lotto, Winner> lottoItemProcessor(LottoService lottoService) {
        return new LottoItemProcessor(lottoService);
    }

    @Bean
    public ItemWriter<Winner> lottoItemWriter(WinnerRepository winnerRepository) {
        return items -> {
            for (Winner item : items) {
                winnerRepository.save(item);
            }
        };
    }


    @Bean
    @Lazy
    public Job lottoJob(Step lottoStep, JobRepository jobRepository) {
        return new JobBuilder("lottoJob", jobRepository)
                .start(lottoStep)
                .build();
    }

    @Bean
    public Step lottoStep(ItemReader<Lotto> lottoItemReader,
                          @Lazy LottoItemProcessor lottoItemProcessor,
                          ItemWriter<Winner> lottoItemWriter,
                          JobRepository jobRepository,
                          PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("lottoStep", jobRepository)
                .<Lotto, Winner>chunk(10, platformTransactionManager)
                .reader(lottoItemReader)
                .processor(lottoItemProcessor)
                .writer(lottoItemWriter)
                .build();
    }

    @Scheduled(cron = "0 0 0 * * SUN") // 매주 일요일 0시에 실행
    public void runLottoJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();

        jobLauncher.run(lottoJob(null, null), jobParameters);
    }
}