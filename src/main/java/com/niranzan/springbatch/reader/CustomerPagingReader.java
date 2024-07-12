package com.niranzan.springbatch.reader;

import com.niranzan.springbatch.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class CustomerPagingReader implements ItemReader<Person> {

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
