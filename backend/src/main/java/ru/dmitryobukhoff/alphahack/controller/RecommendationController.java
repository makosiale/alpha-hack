package ru.dmitryobukhoff.alphahack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmitryobukhoff.alphahack.model.dto.request.RecommendationRequestDto;
import ru.dmitryobukhoff.alphahack.model.dto.response.RecommendationResponseWithMessageDto;
import ru.dmitryobukhoff.alphahack.service.RecommendationService;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping()
    public ResponseEntity<RecommendationResponseWithMessageDto> recommendation(
            @RequestBody RecommendationRequestDto request
            ) {

        RecommendationResponseWithMessageDto response = recommendationService.getRecommendationFromModel(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
