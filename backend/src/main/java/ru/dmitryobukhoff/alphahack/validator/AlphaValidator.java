package ru.dmitryobukhoff.alphahack.validator;

public interface AlphaValidator<T> {
    boolean isValid(T t);
}
