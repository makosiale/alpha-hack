package ru.dmitryobukhoff.alphahack.model.converter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dmitryobukhoff.alphahack.model.converter.Converter;
import ru.dmitryobukhoff.alphahack.model.dto.request.RecommendationMLDto;
import ru.dmitryobukhoff.alphahack.model.dto.request.RecommendationRequestDto;

@Component
@RequiredArgsConstructor
public class RecommendationConverter implements Converter<RecommendationRequestDto, RecommendationMLDto> {

    private final ContextConverter contextConverter;

    @Override
    public RecommendationMLDto convertTo(RecommendationRequestDto recommendationRequestDto) {
        return new RecommendationMLDto(
                recommendationRequestDto.segment(),
                recommendationRequestDto.role(),
                recommendationRequestDto.organizations(),
                recommendationRequestDto.currentMethod(),
                recommendationRequestDto.mobileApp(),
                recommendationRequestDto.signatures(),
                recommendationRequestDto.availableMethods(),
                recommendationRequestDto.reports(),
                contextConverter.convertTo(recommendationRequestDto.context())
        );
    }
}
