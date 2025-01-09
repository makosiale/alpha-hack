package ru.dmitryobukhoff.alphahack.model.converter;

public interface Converter<F, T> {
    T convertTo(F f);
}
