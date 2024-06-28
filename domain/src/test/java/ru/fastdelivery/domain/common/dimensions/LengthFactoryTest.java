package ru.fastdelivery.domain.common.dimensions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LengthFactoryTest {

    @ParameterizedTest(name = "Миллиметры = {arguments} -> объект создан")
    @ValueSource(longs = {0, 1, 100, 10_000})
    @DisplayName("Значение длины больше 0 -> объект не создан")
    void whenLengthGreaterThanZero_thenObjectCreated(long value) {
        var length = new Length(BigInteger.valueOf(value));

        assertNotNull(length);
        assertThat(length.lengthMm()).isEqualByComparingTo(BigInteger.valueOf(value));
    }

    @ParameterizedTest(name = "Миллиметры = {arguments} -> исключение")
    @ValueSource(longs = { -1, -100, -10_000 })
    @DisplayName("Значение длины ниже 0 -> исключение")
    void whenLengthLessThanZero_thenThrowException(long value) {
        assertThatThrownBy(() -> new Length(BigInteger.valueOf(value)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
