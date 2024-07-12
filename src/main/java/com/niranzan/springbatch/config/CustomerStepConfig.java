package com.niranzan.springbatch.config;

import com.niranzan.springbatch.entity.Customer;
import com.niranzan.springbatch.entity.Person;
import com.niranzan.springbatch.processor.CustomerItemProcessor;
import com.niranzan.springbatch.repo.CustomerRepo;
import com.niranzan.springbatch.writer.CustomerItemWriter;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CustomerStepConfig {

    @Bean
    public Step customerStep(JobRepository jobRepository, CustomerRepo customerRepo, PlatformTransactionManager platformTransactionManager, EntityManagerFactory entityManagerFactory) {
        return new StepBuilder("customerStep", jobRepository)
                .<Person, Customer>chunk(1000, platformTransactionManager)
                .reader(customerPagingItemReader(entityManagerFactory))
                .processor(customerItemProcessor())
                .writer(customerItemWriter(customerRepo))
                .build();

    }

    @Bean("customerPagingItemReader")
    public JpaPagingItemReader<Person> customerPagingItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<Person>()
                .entityManagerFactory(entityManagerFactory)
                .name("customerPagingItemReader")
                .queryString("select p from Person p")
                .pageSize(1000)
                .build();
    }

    @Bean("customerItemWriter")
    public ItemWriter<Customer> customerItemWriter(CustomerRepo customerRepo) {
        return new CustomerItemWriter(customerRepo);
    }

    @Bean("customerItemProcessor")
    public ItemProcessor<Person, Customer> customerItemProcessor() {
        return new CustomerItemProcessor();
    }
}
