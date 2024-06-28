package ru.fastdelivery.domain.common.dimensions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OuterDimensionsTest {

    @Test
    @DisplayName("Попытка создать габариты с превышением всех длинн -> исключение")
    void whenAllLengthOfOuterDimensionsMoreThanMaxLength_thenThrowException() {
        var length = new Length(BigInteger.valueOf(1501));
        assertThatThrownBy(() -> new OuterDimensions(length, length, length))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Попытка создать габариты с превышением одной длинны -> исключение")
    void whenOneLengthOfOuterDimensionsMoreThanMaxLength_thenThrowException() {
        var length = new Length(BigInteger.valueOf(1501));
        var normalLength = new Length(BigInteger.valueOf(1000));
        assertThatThrownBy(() -> new OuterDimensions(length, normalLength, normalLength))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Попытка создать габариты с нормальными значениями -> объект создан")
    void whenOuterDimensionsLessThanMaxLength_thenObjectCreated() {
        var actual = new OuterDimensions(new Length(BigInteger.valueOf(1_000)),
                new Length(BigInteger.valueOf(1_000)),
                new Length(BigInteger.valueOf(1_000)));
        assertThat(actual.length()).isEqualTo(new Length(BigInteger.valueOf(1_000)));
    }
}
