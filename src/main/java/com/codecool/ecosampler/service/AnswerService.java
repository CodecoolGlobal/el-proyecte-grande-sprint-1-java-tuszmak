package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.answer.AnswerDTO;
import com.codecool.ecosampler.controller.dto.answer.NewAnswer;
import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    protected void createListOfAnswers(List<NewAnswer> newAnswers, SampleData sampleData) {
        answerRepository.saveAll(
                newAnswers.stream()
                        .map(newAnswer -> new Answer(
                                        UUID.randomUUID(),
                                        newAnswer.answer(),
                                        questionService.getQuestionByPublicId(newAnswer.questionID()),
                                        sampleData
                                )
                        )
                        .toList()
        );
    }

    public UUID modifyAnswer(UUID publicId, AnswerDTO requestAnswer) {
        Answer answer = getAnswerByPublicId(publicId);
        return answerRepository.save(
                        updateAnswerByRequest(requestAnswer, answer)
                )
                .getPublicId();
    }

    protected Answer getAnswerByPublicId(UUID publicId) {
        return answerRepository.findAnswerByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Answer doesn't exist with Id: " + publicId));
    }

    private Answer updateAnswerByRequest(AnswerDTO requestAnswer, Answer answer) {
        if (Objects.nonNull(requestAnswer.answer()))
            answer.setAnswer(requestAnswer.answer());
        return answer;
    }
}
