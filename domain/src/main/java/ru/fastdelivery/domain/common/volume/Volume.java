package ru.fastdelivery.domain.common.volume;

import java.math.BigDecimal;

/**
 * Общий класс объема
 *
 * @param volumeCubM объем в м3
 */
public record Volume(BigDecimal volumeCubM) {

    public Volume {
        if (isLessThanZero(volumeCubM)) {
            throw new IllegalArgumentException("Volume cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigDecimal price) {
        return BigDecimal.ZERO.compareTo(price) > 0;
    }

    public static Volume zero() {
        return new Volume(BigDecimal.ZERO);
    }

    public Volume add(Volume additionalVolume) {
        return new Volume(this.volumeCubM.add(additionalVolume.volumeCubM));
    }
}
