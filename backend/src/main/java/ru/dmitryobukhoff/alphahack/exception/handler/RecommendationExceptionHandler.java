package ru.dmitryobukhoff.alphahack.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import ru.dmitryobukhoff.alphahack.exception.RecommendationRequestValidationException;
import ru.dmitryobukhoff.alphahack.exception.response.ErrorResponse;

import java.time.Instant;

@RestControllerAdvice
public class RecommendationExceptionHandler {

    @ExceptionHandler(RecommendationRequestValidationException.class)
    public ResponseEntity<ErrorResponse> handleRecommendationRequestValidationException(
            RecommendationRequestValidationException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                Instant.now(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleRecommendationRequestValidationException(
            ResourceAccessException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                Instant.now(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
