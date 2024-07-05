package ru.fastdelivery.domain.delivery.point;

import java.math.BigInteger;

public record Distance(BigInteger distanceKm) {

    public Distance {
        if (isLessThanZero(distanceKm)) {
            throw new IllegalArgumentException("Distance cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigInteger value) {
        return BigInteger.ZERO.compareTo(value) > 0;
    }

    public boolean lowerThan(Distance d) {
        return distanceKm().compareTo(d.distanceKm()) < 0;
    }

}
