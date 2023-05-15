package com.azatkhaliullin.englishtest;

import com.azatkhaliullin.englishtest.data.AnswerRepository;
import com.azatkhaliullin.englishtest.data.EnglishLevelRepository;
import com.azatkhaliullin.englishtest.data.EnglishTestRepository;
import com.azatkhaliullin.englishtest.data.QuestionRepository;
import com.azatkhaliullin.englishtest.dto.Answer;
import com.azatkhaliullin.englishtest.dto.EnglishTest;
import com.azatkhaliullin.englishtest.dto.Question;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EnglishTestController {

    private final EnglishTestRepository englishTestRepo;
    private final EnglishLevelRepository englishLevelRepo;
    private final AnswerRepository answerRepo;
    private final QuestionRepository questionRepo;

    public EnglishTestController(EnglishTestRepository englishTestRepo,
                                 EnglishLevelRepository englishLevelRepo,
                                 AnswerRepository answerRepo,
                                 QuestionRepository questionRepo) {
        this.englishTestRepo = englishTestRepo;
        this.englishLevelRepo = englishLevelRepo;
        this.answerRepo = answerRepo;
        this.questionRepo = questionRepo;
    }

    @PostMapping("/test")
    public EnglishTest getEnglishTest() {
        return englishTestRepo.save(new EnglishTest());
    }

    @PostMapping("/delete")
    public void getEnglishTest(@RequestParam Long englishTestId) {
        EnglishTest englishTest = englishTestRepo.getById(englishTestId);
        englishTestRepo.delete(englishTest);
    }

    @PostMapping("/question")
    public Optional<Question> getQuestion(@RequestParam Long englishTestId) {
        Long currentIndex = englishTestRepo.getById(englishTestId).getCurrentIndex();
        if (currentIndex >= questionRepo.count()) {
            return Optional.empty();
        }
        return questionRepo.findById(currentIndex);
    }

    @PostMapping("/answer")
    public void checkAnswer(@RequestParam Long englishTestId,
                            @RequestParam Long answerId) {
        EnglishTest englishTest = englishTestRepo.getById(englishTestId);
        Optional<Answer> optionalAnswer = answerRepo.findById(answerId);
        optionalAnswer.ifPresent(englishTest::checkAnswer);
        englishTestRepo.save(englishTest);
    }

    @PostMapping("/result")
    public String getResult(@RequestParam Long englishTestId) {
        EnglishTest englishTest = englishTestRepo.getById(englishTestId);
        int level = englishTest.calculateLevel();
        return String.format("У вас %d баллов из 162.%n%n%s",
                englishTest.getScore(),
                englishLevelRepo.getByLevel(level).getDescription());
    }

}