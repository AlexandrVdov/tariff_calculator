package ru.fastdelivery.properties_provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.fastdelivery.domain.delivery.point.Degree;
import ru.fastdelivery.properties.provider.CoordinatesProperties;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesPropertiesTest {

    public static final BigDecimal LATITUDE_MIN = BigDecimal.valueOf(45);
    public static final BigDecimal LATITUDE_MAX = BigDecimal.valueOf(65);
    public static final BigDecimal LONGITUDE_MIN = BigDecimal.valueOf(30);
    public static final BigDecimal LONGITUDE_MAX = BigDecimal.valueOf(96);
    CoordinatesProperties properties;

    @BeforeEach
    void init(){
        properties = new CoordinatesProperties();
        properties.setLatitudeMax(LATITUDE_MAX);
        properties.setLatitudeMin(LATITUDE_MIN);
        properties.setLongitudeMax(LONGITUDE_MAX);
        properties.setLongitudeMin(LONGITUDE_MIN);
    }

    @ParameterizedTest(name = "Градус = {arguments} -> посчитано верно")
    @MethodSource("provideDegrees")
    void whenIsInRangeLatitude_thenValidCoordinatesReturnsTrue(Degree degree, boolean expectedResult) {

        assertEquals(expectedResult, properties.isInRangeLatitude(degree));
    }

    @ParameterizedTest(name = "Градус = {arguments} -> посчитано верно")
    @MethodSource("provideDegrees")
    void whenIsInRangeLongitude_thenValidCoordinatesReturnsTrue(Degree degree, boolean expectedResult) {

        assertEquals(expectedResult, properties.isInRangeLongitude(degree));
    }

    static Stream<Arguments> provideDegrees() {
        return Stream.of(
                Arguments.of(new Degree(new BigDecimal(50)), true),
                Arguments.of(new Degree(new BigDecimal(10)), false),
                Arguments.of(new Degree(new BigDecimal(100)), false)
        );
    }

}
