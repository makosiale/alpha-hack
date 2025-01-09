package ru.dmitryobukhoff.alphahack.validator.impl;

import org.springframework.stereotype.Component;
import ru.dmitryobukhoff.alphahack.model.dto.request.RecommendationRequestDto;
import ru.dmitryobukhoff.alphahack.validator.AlphaValidator;

@Component
public class RecommendationRequestValidator implements AlphaValidator<RecommendationRequestDto> {
    @Override
    public boolean isValid(RecommendationRequestDto recommendationRequestDto) {
        return (recommendationRequestDto.context() != null) &&
                (recommendationRequestDto.context().docType() != null) &&
                (recommendationRequestDto.context().transactionAmount() != null) &&
                (recommendationRequestDto.context().deviceType() != null) &&
                (recommendationRequestDto.context().urgency() != null) &&
                (recommendationRequestDto.context().bankSystemIssues() != null) &&
                (recommendationRequestDto.context().clientTypeIssues() != null) &&
                (recommendationRequestDto.context().balanceOpportunity() != null) &&
                (recommendationRequestDto.context().userLocation() != null);
    }
}
