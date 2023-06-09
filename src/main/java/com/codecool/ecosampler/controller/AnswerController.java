package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.answer.AnswerDTO;
import com.codecool.ecosampler.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    private AnswerService answerService;

    @PutMapping("/{publicId}")
    public UUID modifyAnswer(@PathVariable UUID publicId, @RequestBody AnswerDTO requestAnswer) {
        return answerService.modifyAnswer(publicId, requestAnswer);
    }

}
