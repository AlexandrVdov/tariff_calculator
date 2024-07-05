package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.delivery.point.Coordinate;
import ru.fastdelivery.domain.delivery.point.Distance;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class DistanceCalculateUseCase {

    private static final int EARTH_RAD = 6_372_795;
    private final DistancePropertiesProvider distanceProperties;

    public Distance calc(Coordinate destination, Coordinate departure) {

        double llat1 = destination.latitude().degree().doubleValue();
        double llong1 = destination.longitude().degree().doubleValue();
        double llat2 = departure.latitude().degree().doubleValue();
        double llong2 = departure.longitude().degree().doubleValue();

        // Преобразование в радианы
        double lat1 = llat1 * Math.PI / 180;
        double lat2 = llat2 * Math.PI / 180;
        double long1 = llong1 * Math.PI / 180;
        double long2 = llong2 * Math.PI / 180;

        // Вычисление cos и sin
        double cl1 = Math.cos(lat1);
        double cl2 = Math.cos(lat2);
        double sl1 = Math.sin(lat1);
        double sl2 = Math.sin(lat2);

        // Вычисление дельты
        double delta = long2 - long1;
        double cdelta = Math.cos(delta);
        double sdelta = Math.sin(delta);

        // Расчёт длины большого круга
        double y = Math.sqrt(Math.pow(cl2 * sdelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta, 2));
        double x = sl1 * sl2 + cl1 * cl2 * cdelta;
        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RAD;

        return new Distance(BigDecimal.valueOf(dist)
                .divide(new BigDecimal(1000), 0, RoundingMode.HALF_UP)
                .toBigInteger());
    }

    public Distance minimalDistance() {
        return distanceProperties.minimalDistance();
    }
}
