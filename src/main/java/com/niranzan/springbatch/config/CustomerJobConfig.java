package com.niranzan.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerJobConfig {

    private final Step customerStep;

    public CustomerJobConfig(@Qualifier("customerStep") Step customerStep) {
        this.customerStep = customerStep;
    }

    @Bean("customerItemLoaderJob")
    public Job customerItemLoaderJob(JobRepository jobRepository) {
        return new JobBuilder("customerItemLoaderJob", jobRepository)
                .flow(customerStep)
                .end()
                .build();
    }
}
