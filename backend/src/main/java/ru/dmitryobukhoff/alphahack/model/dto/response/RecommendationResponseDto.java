package ru.dmitryobukhoff.alphahack.model.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ru.dmitryobukhoff.alphahack.model.enums.SignatureMethod;

public record RecommendationResponseDto (
        @JsonProperty("recommendation") @Enumerated(EnumType.STRING) SignatureMethod recommendation
){}
