package kr.co.polycube.backendtest.config;

import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import kr.co.polycube.backendtest.service.WinnerService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final WinnerService winnerService;

    @Autowired
    public BatchConfig(WinnerService winnerService) {
        this.winnerService = winnerService;
    }

    @Bean
    public Job lottoWinnerJob(JobRepository jobRepository, Step lottoWinnerStep) {
        return new JobBuilder("lottoWinnerJob", jobRepository)
                .start(lottoWinnerStep)
                .build();
    }

    @Bean
    public Step lottoWinnerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Lotto> lottoItemReader, ItemProcessor<Lotto, Winner> lottoItemProcessor, ItemWriter<Winner> winnerItemWriter) {
        return new StepBuilder("lottoWinnerStep", jobRepository)
                .<Lotto, Winner>chunk(10, transactionManager)
                .reader(lottoItemReader)
                .processor(lottoItemProcessor)
                .writer(winnerItemWriter)
                .build();
    }

    @Bean
    public ItemReader<Lotto> lottoItemReader(LottoRepository lottoRepository) {
        return new RepositoryItemReaderBuilder<Lotto>()
                .name("lottoItemReader")
                .repository(lottoRepository)
                .methodName("findAll")
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<Lotto, Winner> lottoItemProcessor() {
        return winnerService::processLotto;
    }

    @Bean
    public ItemWriter<Winner> winnerItemWriter(WinnerRepository winnerRepository) {
        return winnerRepository::saveAll;
    }
}
