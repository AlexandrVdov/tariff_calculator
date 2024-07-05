package ru.fastdelivery.domain.delivery.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoordinateFactoryTest {

    CoordinatePropertiesProvider mockProvider = mock(CoordinatePropertiesProvider.class);
    CoordinateFactory factory = new CoordinateFactory(mockProvider);

    @Test
    @DisplayName("Координата не входит в разрешенный диапозон -> исключение")
    void whenCoordinateIsInRange_thenThrowException() {
        var latitude = new Degree(new BigDecimal(0));
        var longitude = new Degree(new BigDecimal(0));

        when(mockProvider.isInRangeLatitude(latitude)).thenReturn(false);
        when(mockProvider.isInRangeLongitude(longitude)).thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> factory.create(latitude, longitude));
    }

    @Test
    @DisplayName("Координата входит в разрешенный диапозон -> новый объект")
    void whenCoordinateIsInRange_thenObjectCreated() {
        var latitude = new Degree(new BigDecimal(55));
        var longitude = new Degree(new BigDecimal(55));

        when(mockProvider.isInRangeLatitude(latitude)).thenReturn(true);
        when(mockProvider.isInRangeLongitude(longitude)).thenReturn(true);

        assertThat(factory.create(latitude, longitude)).isNotNull();
    }
}
