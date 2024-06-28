package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.BigInteger;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var volumeAllPackagesCubM = shipment.volumeAllPackages().volumeCubM();
        var minimalPrice = weightPriceProvider.minimalPrice();

        Price weightPrice = weightPriceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg);

        if (volumeAllPackagesCubM.compareTo(new BigDecimal(BigInteger.ZERO)) == 0) {
            return minimalPrice.max(weightPrice);
        }

        Price volumePrice = weightPriceProvider
                .costPerCubicMeter()
                .multiply(volumeAllPackagesCubM);

        return minimalPrice.max(volumePrice).max(weightPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
