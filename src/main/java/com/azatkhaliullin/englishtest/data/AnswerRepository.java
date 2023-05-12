package com.azatkhaliullin.englishtest.data;

import com.azatkhaliullin.englishtest.dto.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnswerRepository
        extends CrudRepository<Answer, Long> {

    Optional<Answer> getById(Long id);

}