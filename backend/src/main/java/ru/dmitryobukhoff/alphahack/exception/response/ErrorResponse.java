package ru.dmitryobukhoff.alphahack.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
public record ErrorResponse (
        @JsonProperty("method") String method,
        @JsonProperty("timestamp") @JsonFormat(pattern = "HH:mm:ss dd.MM.yyyy", timezone = "Europe/Moscow") Instant instant,
        @JsonProperty("description") String description
) {}
