package com.niranzan.springbatch.writer;

import com.niranzan.springbatch.entity.Customer;
import com.niranzan.springbatch.repo.CustomerRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
public class CustomerItemWriter implements ItemWriter<Customer> {

    @NonNull
    private final CustomerRepo customerRepo;

    @Override
    public void write(Chunk<? extends Customer> chunk) throws Exception {
        customerRepo.saveAll(chunk.getItems());
    }
}
