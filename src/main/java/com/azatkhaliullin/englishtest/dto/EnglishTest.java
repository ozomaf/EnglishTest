package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class representing an English test.
 * Contains the test id, current index of the question being answered, and the score obtained so far.
 */
@Entity
@Data
public class EnglishTest {

    @Id
    @GeneratedValue
    private Long id;
    private Long currentIndex;
    private int score;

    public EnglishTest() {
        currentIndex = 0L;
        score = 0;
    }

    /**
     * Checks if the given answer is correct, updates the score and moves on to the next question.
     *
     * @param answer the answer object to be checked.
     */
    public void checkAnswer(Answer answer) {
        if (answer.isRight()) {
            calculateScore();
        }
        currentIndex++;
    }

    /**
     * Calculates the score based on the number of correctly answered questions.
     */
    private void calculateScore() {
        int[] scoreBoundaries = {6, 14, 29, 39, 50};
        int[] points = {1, 2, 3, 4, 5};
        for (int i = 0; i < scoreBoundaries.length; i++) {
            if (currentIndex <= scoreBoundaries[i]) {
                score += points[i];
                break;
            }
        }
    }

    /**
     * Calculates the level based on the score.
     *
     * @return the calculated level.
     */
    public int calculateLevel() {
        int[] levelBoundaries = {10, 30, 50, 80, 100, 120, 140, 156, 162};
        int[] levels = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int level = 0;
        for (int i = 0; i < levelBoundaries.length; i++) {
            if (score < levelBoundaries[i]) {
                level = levels[i];
                break;
            }
        }
        return level;
    }

}