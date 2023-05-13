package com.azatkhaliullin.englishtest.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;

import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    @Setter
    private String question;
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

}