package ru.dmitryobukhoff.alphahack.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.dmitryobukhoff.alphahack.model.enums.*;

public record ContextDto (
        @JsonProperty("docType") DocumentType docType,
        @JsonProperty("transactionAmount") TransactionAmount transactionAmount,
        @JsonProperty("deviceType") DeviceType deviceType,
        @JsonProperty("urgency") Urgency urgency,
        @JsonProperty("bankSystemIssues") BankSystemIssues bankSystemIssues,
        @JsonProperty("clientTypeIssues") ClientTypeIssues clientTypeIssues,
        @JsonProperty("balanceOpportunity") BalanceOpportunity balanceOpportunity,
        @JsonProperty("userLocation") UserLocation userLocation
){}
