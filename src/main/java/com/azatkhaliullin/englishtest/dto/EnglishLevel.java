package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class EnglishLevel {

    @Id
    private int level;
    @Getter
    @Column(columnDefinition = "text")
    private String description;

}