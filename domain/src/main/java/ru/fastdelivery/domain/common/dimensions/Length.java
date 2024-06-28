package ru.fastdelivery.domain.common.dimensions;

import java.math.BigInteger;

/**
 * Общий класс длины
 *
 * @param lengthMm длина в миллиметрах
 */
public record Length(BigInteger lengthMm) {

    public Length {
        if (isLessThanZero(lengthMm)) {
            throw new IllegalArgumentException("Length cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigInteger size) {
        return BigInteger.ZERO.compareTo(size) > 0;
    }

    public static Length fromMillimeter(BigInteger length) {
        return new Length(length);
    }

    public boolean isLongerThan(Length l) {
        return lengthMm().compareTo(l.lengthMm()) > 0;
    }
}
