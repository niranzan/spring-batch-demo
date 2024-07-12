package com.niranzan.springbatch.config;

import com.niranzan.springbatch.repo.CustomerRepo;
import com.niranzan.springbatch.writer.CustomerItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemWriteConfig {

   /* @Bean("customerItemWriter")
    public CustomerItemWriter customerItemWriter(CustomerRepo customerRepo) {
        return new CustomerItemWriter(customerRepo);
    }*/
}
