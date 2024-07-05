package ru.fastdelivery.domain.delivery.point;

/**
 * Координаты пункта
 *
 * @param latitude широта
 * @param longitude долгота
 */
public record Coordinate(Degree latitude,
                         Degree longitude) {
}
