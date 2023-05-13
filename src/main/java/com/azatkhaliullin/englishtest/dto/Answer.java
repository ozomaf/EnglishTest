package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    private Long id;
    @Setter
    private String value;
    @Setter
    @Getter
    private boolean isRight;

}