package com.azatkhaliullin.englishtest.data;

import com.azatkhaliullin.englishtest.dto.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository
        extends CrudRepository<Question, Long> {
}