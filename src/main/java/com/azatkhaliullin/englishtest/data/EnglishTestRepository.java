package com.azatkhaliullin.englishtest.data;

import com.azatkhaliullin.englishtest.EnglishTest;
import org.springframework.data.repository.CrudRepository;

public interface EnglishTestRepository
        extends CrudRepository<EnglishTest, Long> {

    EnglishTest getById(Long id);

}