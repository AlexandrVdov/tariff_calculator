package ru.fastdelivery.domain.common.dimensions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LengthTest {

    @Test
    @DisplayName("Попытка создать отрицательную длинну -> исключение")
    void whenLengthBelowZero_thenException() {
        var length = new BigInteger("-1");
        assertThatThrownBy(() -> new Length(length))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equalsTypeWidth_same() {
        var length = new Length(new BigInteger("1000"));
        var lengthSame = new Length(new BigInteger("1000"));

        assertThat(length)
                .isEqualTo(lengthSame)
                .hasSameHashCodeAs(lengthSame);
    }

    @Test
    void equalsNull_false() {
        var length = new Length(new BigInteger("4"));

        assertThat(length).isNotEqualTo(null);
    }

    @Test
    @DisplayName("Первая длинна больше второй -> true")
    void whenFirstLengthLongerThanSecond_thenTrue() {
        var lengthBig = new Length(new BigInteger("1001"));
        var lengthSmall = new Length(new BigInteger("1000"));

        assertThat(lengthBig.isLongerThan(lengthSmall)).isTrue();
    }

    @Test
    @DisplayName("Запрос создания длинны в мм -> получен корректный объект")
    void whenFromMillimeter_thenReceiveMillimeter() {
        var actual = Length.fromMillimeter(new BigInteger("1001"));

        assertNotNull(actual);
        assertThat(actual.lengthMm()).isEqualByComparingTo(new BigInteger("1001"));
    }
}
