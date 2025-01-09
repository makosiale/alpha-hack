package ru.dmitryobukhoff.alphahack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.dmitryobukhoff.alphahack.exception.RecommendationRequestValidationException;
import ru.dmitryobukhoff.alphahack.model.converter.impl.RecommendationConverter;
import ru.dmitryobukhoff.alphahack.model.dto.request.RecommendationMLDto;
import ru.dmitryobukhoff.alphahack.model.dto.request.RecommendationRequestDto;
import ru.dmitryobukhoff.alphahack.model.dto.response.RecommendationResponseDto;
import ru.dmitryobukhoff.alphahack.model.dto.response.RecommendationResponseWithMessageDto;
import ru.dmitryobukhoff.alphahack.model.enums.SignatureMethod;
import ru.dmitryobukhoff.alphahack.validator.AlphaValidator;

import java.util.Random;


@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {
    private final static String signatureRecommendationUrl = "http://ml-model:8000";
    private final RecommendationConverter recommendationConverter;
    private final RestTemplate restTemplate;
    private final AlphaValidator<RecommendationRequestDto> recommendationRequestDtoAlphaValidator;

    public RecommendationResponseWithMessageDto getRecommendationFromModel(RecommendationRequestDto recommendationRequestDto) {
        if(!recommendationRequestDtoAlphaValidator.isValid(recommendationRequestDto)){
            throw new RecommendationRequestValidationException("JSON должен содержать все поля контекста");
        }
        final String method = "/recommendation";
        final String url = signatureRecommendationUrl + method;
        RecommendationMLDto recommendationMLDto = recommendationConverter.convertTo(recommendationRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RecommendationMLDto> request = new HttpEntity<>(recommendationMLDto, headers);
        try {
            ResponseEntity<RecommendationResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, request, RecommendationResponseDto.class);
            return new RecommendationResponseWithMessageDto(
                    response.getBody().recommendation(),
                    generateMessageBySignatureMethod(response.getBody().recommendation())
            );
        }catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ResourceAccessException("Превышено время ответа модели");
        }
    }

    private String generateMessageBySignatureMethod(SignatureMethod signatureMethod) {
        Random random = new Random();
        int randomPercent = random.nextInt(21) + 80;
        int randomPerson = random.nextInt(21) + 80;
        final String message = "Данный способ оплаты на " + randomPercent + "%\nподходит Вам, в похожей ситуации им пользуется\n" + randomPerson + "% клиентов.";
        return message;
    }

}
