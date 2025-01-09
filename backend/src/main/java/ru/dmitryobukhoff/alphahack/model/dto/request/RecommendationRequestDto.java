package ru.dmitryobukhoff.alphahack.model.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ru.dmitryobukhoff.alphahack.model.enums.Segment;
import ru.dmitryobukhoff.alphahack.model.enums.ClientRole;
import ru.dmitryobukhoff.alphahack.model.enums.SignatureMethod;

import java.util.Set;

public record RecommendationRequestDto(
        @JsonProperty("clientId") Long clientId,
        @JsonProperty("organizationId") Long organizationId,
        @JsonProperty("segment") @Enumerated(EnumType.STRING) Segment segment,
        @JsonProperty("role") @Enumerated(EnumType.STRING) ClientRole role,
        @JsonProperty("organizations") Integer organizations,
        @JsonProperty("currentMethod") SignatureMethod currentMethod,
        @JsonProperty("mobileApp") Boolean mobileApp,
        @JsonProperty("signatures") SignaturesListDto signatures,
        @JsonProperty("availableMethods")Set<SignatureMethod> availableMethods,
        @JsonProperty("reports") SignaturesHistoryDto reports,
        @JsonProperty("context") ContextDto context
        ) {}
