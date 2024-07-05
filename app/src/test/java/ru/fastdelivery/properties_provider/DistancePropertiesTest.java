package ru.fastdelivery.properties_provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.properties.provider.DistanceProperties;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class DistancePropertiesTest {

    public static final BigInteger MINIMAL = BigInteger.valueOf(450);
    DistanceProperties properties;

    @BeforeEach
    void init(){
        properties = new DistanceProperties();
        properties.setDistanceMin(MINIMAL);
    }

    @Test
    void whenCallMinimalDistance_thenRequestFromConfig() {
        var actual = properties.minimalDistance();

        assertThat(actual.distanceKm()).isEqualByComparingTo(MINIMAL);
    }
}
