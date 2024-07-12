package com.niranzan.springbatch.writer;

import com.niranzan.springbatch.entity.Person;
import com.niranzan.springbatch.repo.PersonRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
public class PersonItemWriter implements ItemWriter<Person> {

    @NonNull
    private final PersonRepo personRepo;

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        personRepo.saveAll(chunk.getItems());
    }
}
