package ru.dmitryobukhoff.alphahack.model.converter.impl;

import org.springframework.stereotype.Component;
import ru.dmitryobukhoff.alphahack.model.converter.Converter;
import ru.dmitryobukhoff.alphahack.model.dto.request.ContextDto;
import ru.dmitryobukhoff.alphahack.model.dto.request.ContextMLDto;

@Component
public class ContextConverter implements Converter<ContextDto, ContextMLDto> {

    @Override
    public ContextMLDto convertTo(ContextDto contextDto) {
        return new ContextMLDto(
                contextDto.docType(),
                contextDto.transactionAmount(),
                contextDto.deviceType(),
                contextDto.urgency(),
                contextDto.bankSystemIssues(),
                contextDto.clientTypeIssues(),
                contextDto.balanceOpportunity(),
                contextDto.userLocation()
        );
    }
}
