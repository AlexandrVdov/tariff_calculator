package ru.fastdelivery.domain.delivery.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DegreeTest {

    @Test
    @DisplayName("Первый градус больше второго -> true")
    void whenFirstDegreeGreaterThanSecond_thenTrue() {
        var degreeBig = new Degree(new BigDecimal("1001.0"));
        var degreeSmall = new Degree(new BigDecimal("1000.0"));

        assertThat(degreeBig.greaterThan(degreeSmall)).isTrue();
    }

    @Test
    @DisplayName("Первый градус меньше второго -> true")
    void whenFirstDegreeLowerThanSecond_thenTrue() {
        var degreeBig = new Degree(new BigDecimal("1001.0"));
        var degreeSmall = new Degree(new BigDecimal("1000.0"));

        assertThat(degreeSmall.lowerThan(degreeBig)).isTrue();
    }
}
