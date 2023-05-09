package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.repository.AnswerRepository;
import com.codecool.ecosampler.utilities.AnswerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AnswerServiceTest {

    private AnswerRepository answerRepository;
    private AnswerService answerService;
    private QuestionService questionService;


    @BeforeEach
    public void setup() {
        answerRepository = Mockito.mock(AnswerRepository.class);
        answerService = new AnswerService(answerRepository, new AnswerMapper(), questionService);

    }


    @Test
    void getAllAnswersDTO() {
        Answer answer = new Answer(UUID.randomUUID(), "Waaaaa", new Question());
        when(answerRepository.findAll()).thenReturn(List.of(answer));
        assertEquals(1, answerService.getAllAnswersDTO().size());
    }

    @Test
    void createAnswer() {
    }

    @Test
    void modifyAnswer() {
    }

    @Test
    void getAnswerByPublicId() {
    }
}