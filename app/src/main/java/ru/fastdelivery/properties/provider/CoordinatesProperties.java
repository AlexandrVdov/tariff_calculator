package ru.fastdelivery.properties.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.delivery.point.CoordinatePropertiesProvider;
import ru.fastdelivery.domain.delivery.point.Degree;

import java.math.BigDecimal;

/**
 * Настройки широт из конфига
 */
@Configuration
@ConfigurationProperties("geographic.coordinate")
@Setter
@Getter
public class CoordinatesProperties implements CoordinatePropertiesProvider {

    private BigDecimal latitudeMin;
    private BigDecimal latitudeMax;

    private BigDecimal longitudeMin;
    private BigDecimal longitudeMax;


    @Override
    public boolean isInRangeLatitude(Degree degree) {
        return degree.greaterThan(new Degree(latitudeMin))
                && degree.lowerThan(new Degree(latitudeMax));
    }

    @Override
    public boolean isInRangeLongitude(Degree degree) {
        return degree.greaterThan(new Degree(longitudeMin))
                && degree.lowerThan(new Degree(longitudeMax));
    }

}
