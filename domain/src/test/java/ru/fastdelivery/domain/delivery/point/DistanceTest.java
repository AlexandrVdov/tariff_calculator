package ru.fastdelivery.domain.delivery.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DistanceTest {

    @Test
    @DisplayName("Попытка создать отрицательное расстояние -> исключение")
    void whenDistanceBelowZero_thenException() {
        var distance = new BigInteger("-1");
        assertThatThrownBy(() -> new Distance(distance))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Первое расстояние меньше второго -> true")
    void whenFirstDistanceLowerThanThanSecond_thenTrue() {
        var distanceBig = new Distance(new BigInteger("1001"));
        var distanceSmall = new Distance(new BigInteger("1000"));

        assertThat(distanceSmall.lowerThan(distanceBig)).isTrue();
    }
}
