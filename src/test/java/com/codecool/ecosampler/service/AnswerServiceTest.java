package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.answer.NewAnswer;
import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.domain.FieldStyle;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {


    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private AnswerService answerService;

    @Test
    void should_save_one_answer() {
        SampleData sampleData = new SampleData();
        UUID questionId = UUID.randomUUID();
        NewAnswer answer1 = new NewAnswer("Test", questionId);
        when(questionService.getQuestionByPublicId(any(UUID.class)))
                .thenReturn(new Question(
                        questionId,
                        "Question 1",
                        FieldStyle.SHORT_TEXT)
                );
        answerService.createListOfAnswers(List.of(answer1), sampleData);

        verify(answerRepository).saveAll(argThat(answerList -> {
            List<Answer> answers = StreamSupport.stream(answerList.spliterator(), false)
                    .toList();
            return answers.size() == 1 &&
                    answers.get(0).getAnswer().equals("Test") &&
                    answers.get(0).getQuestion().getDescription().equals("Question 1") &&
                    answers.get(0).getSampleData().equals(sampleData);
        }));
    }

    @Test
    void should_save_multiple_answers() {
        SampleData sampleData = new SampleData();
        UUID questionId1 = UUID.randomUUID();
        UUID questionId2 = UUID.randomUUID();
        NewAnswer answer1 = new NewAnswer("Test 1", questionId1);
        NewAnswer answer2 = new NewAnswer("Test 2", questionId2);
        when(questionService.getQuestionByPublicId(questionId1))
                .thenReturn(new Question(
                        questionId1,
                        "Question 1",
                        FieldStyle.SHORT_TEXT)
                );
        when(questionService.getQuestionByPublicId(questionId2))
                .thenReturn(new Question(
                        questionId2,
                        "Question 2",
                        FieldStyle.SHORT_TEXT)
                );
        answerService.createListOfAnswers(List.of(answer1, answer2), sampleData);

        verify(answerRepository).saveAll(argThat(answerList -> {
            List<Answer> answers = StreamSupport.stream(answerList.spliterator(), false)
                    .toList();
            return answers.size() == 2 &&
                    answers.get(0).getAnswer().equals("Test 1") &&
                    answers.get(0).getQuestion().getDescription().equals("Question 1") &&
                    answers.get(0).getSampleData().equals(sampleData) &&
                    answers.get(1).getAnswer().equals("Test 2") &&
                    answers.get(1).getQuestion().getDescription().equals("Question 2") &&
                    answers.get(1).getSampleData().equals(sampleData);
        }));
    }

        //Probably not needed
//    @Test
//    void empty_answer_list() {
//        SampleData sampleData = new SampleData();
//        UUID questionId = UUID.randomUUID();
//
//        when(questionService.getQuestionByPublicId(questionId))
//                .thenReturn(new Question(
//                        questionId,
//                        "Question 1",
//                        FieldStyle.SHORT_TEXT)
//                );
//        answerService.createListOfAnswers(Collections.emptyList(), sampleData);
//
//        verify(answerRepository).saveAll(argThat(answerList -> {
//            List<Answer> answers = StreamSupport.stream(answerList.spliterator(), false)
//                    .toList();
//            return answers.size() == 0;
//        }));
//    }

    @Test
    void modifyAnswer() {
    }

    @Test
    void getAnswerByPublicId() {
    }
}