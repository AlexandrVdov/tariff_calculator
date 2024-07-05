package ru.fastdelivery.domain.delivery.point;

import java.math.BigDecimal;

/**
 * Общий класс градус
 *
 * @param degree градус
 */
public record Degree(BigDecimal degree) {

    public boolean greaterThan(Degree d) {
        return degree().compareTo(d.degree()) > 0;
    }

    public boolean lowerThan(Degree d) {
        return degree().compareTo(d.degree()) < 0;
    }
}
