package ru.fastdelivery.properties.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.delivery.point.Distance;
import ru.fastdelivery.usecase.DistancePropertiesProvider;

import java.math.BigInteger;

/**
 * Настройки минимального расстояния для рассчета из конфига
 */
@Configuration
@ConfigurationProperties("distance")
@Setter
@Getter
public class DistanceProperties implements DistancePropertiesProvider {

    private BigInteger distanceMin;

    @Override
    public Distance minimalDistance() {
        return new Distance(distanceMin);
    }
}
