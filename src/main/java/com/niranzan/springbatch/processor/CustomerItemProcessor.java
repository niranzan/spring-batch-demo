package com.niranzan.springbatch.processor;

import com.niranzan.springbatch.entity.Customer;
import com.niranzan.springbatch.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class CustomerItemProcessor implements ItemProcessor<Person, Customer> {

    @Override
    public Customer process(Person item) throws Exception {
        return new Customer(item.getId(),
                item.getName(),
                item.getCity(),
                "Male");
    }
}
