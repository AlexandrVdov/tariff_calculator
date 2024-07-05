package ru.fastdelivery.domain.delivery.point;

import lombok.RequiredArgsConstructor;

/**
 * Создание координат пункта с проверками
 */
@RequiredArgsConstructor
public class CoordinateFactory {

    private final CoordinatePropertiesProvider coordinatePropertiesProvider;

    public Coordinate create(Degree latitude, Degree longitude) {
        if (!coordinatePropertiesProvider.isInRangeLatitude(latitude)) {
            throw new IllegalArgumentException("latitude isn't in correct range");
        }
        if (!coordinatePropertiesProvider.isInRangeLongitude(longitude)) {
            throw new IllegalArgumentException("longitude isn't in correct range");
        }

        return new Coordinate(latitude, longitude);
    }
}
