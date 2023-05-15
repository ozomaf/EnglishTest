package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Answer {

    @Id
    private Long id;
    private String value;
    private boolean isRight;

}