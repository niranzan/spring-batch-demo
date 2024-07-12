package com.niranzan.springbatch.config;

import com.niranzan.springbatch.domain.PersonDomain;
import com.niranzan.springbatch.entity.Person;
import com.niranzan.springbatch.processor.PersonItemProcessor;
import com.niranzan.springbatch.repo.PersonRepo;
import com.niranzan.springbatch.writer.PersonItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    @Bean("personItemLoaderJob")
    public Job personItemLoaderJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, PersonRepo personRepo) {
        return new JobBuilder("personItemLoaderJob", jobRepository)
                .flow(personItemLoaderStep(jobRepository, platformTransactionManager, personRepo))
                .end()
                .build();
    }

    @Bean
    public Step personItemLoaderStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, PersonRepo personRepo) {
        return new StepBuilder("personItemLoaderStep", jobRepository)
                .<PersonDomain, Person>chunk(10, platformTransactionManager)
                .reader(personItemReader())
                .processor(personItemProcessor())
                .writer(personItemWriter(personRepo))
                .build();
    }

    @Bean
    public FlatFileItemReader<PersonDomain> personItemReader() {
        return new FlatFileItemReaderBuilder<PersonDomain>()
                .name("personItemReader")
                .delimited()
                .names("id","name","city","age")
                .targetType(PersonDomain.class)
                .resource(new FileSystemResource("src/main/resources/person_data.csv"))
                .build();
    }

    @Bean
    public PersonItemProcessor personItemProcessor() {
        return new PersonItemProcessor();
    }

    @Bean
    public PersonItemWriter personItemWriter(PersonRepo personRepo) {
        return new PersonItemWriter(personRepo);
    }
}
