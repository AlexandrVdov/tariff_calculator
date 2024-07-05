package ru.fastdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;
import ru.fastdelivery.domain.delivery.point.CoordinateFactory;
import ru.fastdelivery.domain.delivery.point.CoordinatePropertiesProvider;
import ru.fastdelivery.usecase.DistanceCalculateUseCase;
import ru.fastdelivery.usecase.DistancePropertiesProvider;
import ru.fastdelivery.usecase.TariffCalculateUseCase;
import ru.fastdelivery.usecase.WeightPriceProvider;

/**
 * Определение реализаций бинов для всех модулей приложения
 */
@Configuration
public class Beans {

    @Bean
    public CurrencyFactory currencyFactory(CurrencyPropertiesProvider currencyProperties) {
        return new CurrencyFactory(currencyProperties);
    }

    @Bean
    public TariffCalculateUseCase tariffCalculateUseCase(WeightPriceProvider weightPriceProvider) {
        return new TariffCalculateUseCase(weightPriceProvider);
    }

    @Bean
    public CoordinateFactory coordinateFactory(CoordinatePropertiesProvider coordinatePropertiesProvider) {
        return new CoordinateFactory(coordinatePropertiesProvider);
    }

    @Bean
    public DistanceCalculateUseCase distanceCalculateUseCase(DistancePropertiesProvider distancePropertiesProvider) {
        return new DistanceCalculateUseCase(distancePropertiesProvider);
    }
}
