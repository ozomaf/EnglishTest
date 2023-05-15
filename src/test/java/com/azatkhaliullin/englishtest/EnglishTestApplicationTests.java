package com.azatkhaliullin.englishtest;

import com.azatkhaliullin.englishtest.data.AnswerRepository;
import com.azatkhaliullin.englishtest.data.EnglishLevelRepository;
import com.azatkhaliullin.englishtest.data.EnglishTestRepository;
import com.azatkhaliullin.englishtest.data.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
class EnglishTestApplicationTests {

    @MockBean
    EnglishTestRepository englishTestRepo;
    @MockBean
    EnglishLevelRepository englishLevelRepo;
    @MockBean
    AnswerRepository answerRepo;
    @MockBean
    QuestionRepository questionRepo;

    @Test
    void contextLoads() {
    }

}