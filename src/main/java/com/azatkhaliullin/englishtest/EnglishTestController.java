package com.azatkhaliullin.englishtest;

import com.azatkhaliullin.englishtest.data.AnswerRepository;
import com.azatkhaliullin.englishtest.data.EnglishLevelRepository;
import com.azatkhaliullin.englishtest.data.EnglishTestRepository;
import com.azatkhaliullin.englishtest.dto.Answer;
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

    public EnglishTestController(EnglishTestRepository englishTestRepo,
                                 EnglishLevelRepository englishLevelRepo,
                                 AnswerRepository answerRepo) {
        this.englishTestRepo = englishTestRepo;
        this.englishLevelRepo = englishLevelRepo;
        this.answerRepo = answerRepo;
    }

    @PostMapping("/test")
    public EnglishTest getEnglishTest() {
        return englishTestRepo.save(new EnglishTest());
    }

    @PostMapping("/question")
    public Optional<Question> getQuestion(@RequestParam Long idEnglishTest) {
        EnglishTest englishTest = englishTestRepo.getById(idEnglishTest);
        return EnglishTestUtility.getNextQuestion(englishTest, englishTestRepo);
    }

    @PostMapping("/answer")
    public void checkAnswer(@RequestParam Long idEnglishTest,
                            @RequestParam Long idAnswer) {
        EnglishTest englishTest = englishTestRepo.getById(idEnglishTest);
        Optional<Answer> optionalAnswer = answerRepo.getById(idAnswer);
        optionalAnswer.ifPresent(answer -> EnglishTestUtility.checkAnswer(englishTest, answer));
        englishTestRepo.save(englishTest);
    }

    @PostMapping("/result")
    public String getResult(@RequestParam Long idEnglishTest) {
        if (idEnglishTest != null) {
            EnglishTest englishTest = englishTestRepo.getById(idEnglishTest);
            int level = englishTest.calculateLevel();
            return String.format("У вас %d баллов из 162.%n%n%s",
                    englishTest.getScore(),
                    englishLevelRepo.getByLevel(level).getDescription());
        } else {
            return "Упс, кажется вы еще не проходили тест";
        }
    }

}