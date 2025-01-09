package ru.dmitryobukhoff.alphahack.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignaturesHistoryDto (
        @JsonProperty("SMS") Integer signedBySMS,
        @JsonProperty("PAY_CONTROL") Integer signedByPayControl,
        @JsonProperty("QS_TOKEN") Integer signedByQSToken,
        @JsonProperty("QS_MOBILE") Integer signedByQSMobile
){
}
