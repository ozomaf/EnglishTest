package com.azatkhaliullin.englishtest.data;

import com.azatkhaliullin.englishtest.dto.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository
        extends CrudRepository<Answer, Long> {
}