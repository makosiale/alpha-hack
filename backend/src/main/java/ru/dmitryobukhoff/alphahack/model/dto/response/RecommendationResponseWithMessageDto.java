package ru.dmitryobukhoff.alphahack.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.dmitryobukhoff.alphahack.model.enums.SignatureMethod;

public record RecommendationResponseWithMessageDto(
        @JsonProperty("signatureMethod")SignatureMethod signatureMethod,
        @JsonProperty("information") String information
) {}
