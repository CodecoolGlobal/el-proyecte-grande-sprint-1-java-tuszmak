package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.sampledata.NewSampleData;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.service.SampleDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sampledata")
public class SampleDataController {
    SampleDataService sampleDataService;

    @GetMapping
    public List<SampleData> getAllSampleData() {
        return sampleDataService.getAllSampleData();
    }

    @GetMapping("/{id}")
    public SampleData getSpecificSampleData(@PathVariable Long id) {
        return sampleDataService.getSpecificSampleData(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Long createQuestion(@RequestBody NewSampleData sampleData) {
        return sampleDataService.createSampleData(sampleData);
    }

    @DeleteMapping("/{id}")
    public void deleteSampleData(@PathVariable Long id) {
        sampleDataService.deleteSampleData(id);
    }

}
