package com.azatkhaliullin.englishtest;


import com.azatkhaliullin.englishtest.data.EnglishTestRepository;
import com.azatkhaliullin.englishtest.dto.Answer;
import com.azatkhaliullin.englishtest.dto.Question;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * A utility class for handling EnglishTest related functionalities.
 */
@UtilityClass
public class EnglishTestUtility {

    /**
     * Returns the next question from the given EnglishTest object and saves the object to the repository.
     *
     * @param englishTest     the EnglishTest object.
     * @param englishTestRepo the repository object for EnglishTest.
     * @return the next question from the given EnglishTest object as an Optional object.
     */
    public Optional<Question> getNextQuestion(EnglishTest englishTest,
                                              EnglishTestRepository englishTestRepo) {
        Optional<Question> optionalQuestion = englishTest.getNextQuestion();
        englishTestRepo.save(englishTest); // TODO зачем я его сохраняю, изменений никаких нет
        return optionalQuestion;
    }

    /**
     * Checks if the given answer is correct, updates the score and moves on to the next question.
     *
     * @param englishTest the EnglishTest object.
     * @param answer      the answer object.
     */
    public void checkAnswer(EnglishTest englishTest,
                            Answer answer) {
        if (answer.isRight()) {
            // If the answer is correct, increment the score.
            englishTest.calculateScore();
        }
        // Move on to the next question.
        englishTest.incrementCurrentIndex();
    }

}
