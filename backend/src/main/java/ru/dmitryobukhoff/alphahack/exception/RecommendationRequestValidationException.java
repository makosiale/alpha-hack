package ru.dmitryobukhoff.alphahack.exception;

public class RecommendationRequestValidationException extends RuntimeException{
    public RecommendationRequestValidationException(String message) {
        super(message);
    }
}
