package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.service.FormService;
import com.codecool.ecosampler.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    private QuestionService questionService;
    private FormService formService;

    @GetMapping("/by-form-id/{formId}")
    List<QuestionDTO> getFormQuestionsByFormID(@PathVariable UUID formId) {
        return formService.getQuestionDTOsByFormID(formId);
    }

    @PutMapping("/{publicId}")
    public UUID modifyQuestion(@PathVariable UUID publicId, @RequestBody QuestionDTO newQuestion) {
        return questionService.modifyQuestion(publicId, newQuestion);
    }

    @DeleteMapping("/{publicId}")
    public void deleteQuestion(@PathVariable UUID publicId) {
        questionService.deleteQuestion(publicId);
    }
}
