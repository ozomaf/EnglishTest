package com.azatkhaliullin.englishtest.data;

import com.azatkhaliullin.englishtest.dto.EnglishLevel;
import org.springframework.data.repository.CrudRepository;

public interface EnglishLevelRepository
        extends CrudRepository<EnglishLevel, Long> {

    EnglishLevel getByLevel(int level);

}