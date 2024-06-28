package ru.fastdelivery.domain.common.dimensions;

import java.math.BigInteger;

/**
 * Габариты
 *
 * @param length Длина
 * @param width Ширина
 * @param height Высота
 */
public record OuterDimensions(
        Length length,
        Length width,
        Length height) {

    private static final BigInteger MAX_LENGTH_DIMENSION = BigInteger.valueOf(1500);

    public OuterDimensions {
        var maxLength = Length.fromMillimeter(MAX_LENGTH_DIMENSION);
        if (length.isLongerThan(maxLength) || width.isLongerThan(maxLength) || height.isLongerThan(maxLength)) {
            throw new IllegalArgumentException("length is longer than maxLength");
        }
    }
}
