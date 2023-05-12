package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    private Long id;
    private String value;
    @Getter
    private boolean isRight;

}