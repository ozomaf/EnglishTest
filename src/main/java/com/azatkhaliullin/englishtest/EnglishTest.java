package com.azatkhaliullin.englishtest;

import com.azatkhaliullin.englishtest.dto.Answer;
import com.azatkhaliullin.englishtest.dto.Question;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents an English test with a list of questions, current index, level, and score.
 * Test questions are loaded from a JSON file located in the resources' folder.
 */
@Entity
@Slf4j
@Data
public class EnglishTest {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Question> questions;
    private int currentIndex;
    private int level;
    private int score;

    public EnglishTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource("questions.json");
            questions = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Failed to upload test questions", e);
        }
        this.questions = questions;
        currentIndex = 0;
        level = 0;
        score = 0;
    }

    /**
     * Returns the next question in the test, if available.
     *
     * @return an Optional object containing the next question or empty if no more questions are available.
     */
    public Optional<Question> getNextQuestion() {
        if (currentIndex >= questions.size()) {
            return Optional.empty();
        }
        return Optional.of(questions.get(currentIndex));
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
        for (int i = 0; i < levelBoundaries.length; i++) {
            if (score < levelBoundaries[i]) {
                level = levels[i];
                break;
            }
        }
        return level;
    }

}