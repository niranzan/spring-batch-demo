package com.niranzan.springbatch.processor;

import com.niranzan.springbatch.domain.PersonDomain;
import com.niranzan.springbatch.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class PersonItemProcessor implements ItemProcessor<PersonDomain, Person> {

    @Override
    public Person process(PersonDomain item) throws Exception {
        return new Person(
                item.getId(),
                item.getName(),
                item.getCity(),
                item.getAge()
        );
    }
}
