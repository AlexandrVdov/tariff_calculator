package ru.fastdelivery.usecase;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.fastdelivery.domain.delivery.point.Coordinate;
import ru.fastdelivery.domain.delivery.point.Degree;
import ru.fastdelivery.domain.delivery.point.Distance;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DistanceCalculateUseCaseTest {

    final DistancePropertiesProvider distancePropertiesProvider = mock(DistancePropertiesProvider.class);
    final DistanceCalculateUseCase distanceCalculateUseCase = new DistanceCalculateUseCase(distancePropertiesProvider);

    @ParameterizedTest(name = "Координаты = {arguments} -> посчитано верно")
    @CsvSource({ "77.153900, -139.398000, -77.180400, -139.550000, 17166",
            "77.153900, 120.398000, 77.180400, 129.550000, 226",
            "77.153900, -120.398000, 77.180400, 129.550000, 2333" })
    @DisplayName("Расчет расстояния по разным координатам -> успешно")
    void whenCalculateDistanceByDifferentCoordinate_thenSuccess(double lat1, double long1,
                                                                double lat2, double long2,
                                                                int expected) {

        Coordinate destination = new Coordinate(new Degree(BigDecimal.valueOf(lat1)), new Degree(BigDecimal.valueOf(long1)));
        Coordinate departure = new Coordinate(new Degree(BigDecimal.valueOf(lat2)), new Degree(BigDecimal.valueOf(long2)));

        var expectedDistance = new Distance(BigInteger.valueOf(expected));

        var actualDistance = distanceCalculateUseCase.calc(destination, departure);

        assertThat(actualDistance).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expectedDistance);
    }

    @Test
    @DisplayName("Получение минимального расстояния -> успешно")
    void whenMinimalDistance_thenSuccess() {
        var minimalDistance = new Distance(BigInteger.TEN);
        when(distancePropertiesProvider.minimalDistance()).thenReturn(minimalDistance);

        var actual = distanceCalculateUseCase.minimalDistance();

        assertThat(actual).isEqualTo(minimalDistance);
    }
}
