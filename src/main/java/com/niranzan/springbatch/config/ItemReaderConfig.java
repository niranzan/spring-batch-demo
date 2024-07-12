package com.niranzan.springbatch.config;

import com.niranzan.springbatch.entity.Person;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ItemReaderConfig {

/*    @Bean("customerPagingItemReader")
    public JpaPagingItemReader<Person> customerPagingItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<Person>()
                .entityManagerFactory(entityManagerFactory)
                .name("customerPagingItemReader")
                .queryString("select p from Person p")
                .pageSize(1000)
                .build();
    }*/
}
