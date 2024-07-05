package ru.fastdelivery.usecase;

import ru.fastdelivery.domain.delivery.point.Distance;

import java.math.BigInteger;

public interface DistancePropertiesProvider {

    Distance minimalDistance();
}
