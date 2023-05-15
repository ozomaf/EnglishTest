package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {

    @Id
    private Long id;
    private String question;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

}