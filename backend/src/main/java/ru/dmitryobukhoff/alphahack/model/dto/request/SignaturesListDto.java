package ru.dmitryobukhoff.alphahack.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignaturesListDto (
    @JsonProperty("common") SignaturesHistoryDto common,
    @JsonProperty("special") SignaturesHistoryDto special
) {
}
