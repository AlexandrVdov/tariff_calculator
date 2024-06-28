package ru.fastdelivery.domain.delivery.shipment;

import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.volume.Volume;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

/**
 * @param packages упаковки в грузе
 * @param currency валюта объявленная для груза
 */
public record Shipment(
        List<Pack> packages,
        Currency currency
) {
    public Weight weightAllPackages() {
        return packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
    }

    public Volume volumeAllPackages() {
        return packages.stream()
                .map(this::countVolumePack)
                .reduce(Volume.zero(), Volume::add);
    }

    private Volume countVolumePack(Pack pack) {
        BigInteger height = normalizeSize(pack.outerDimensions().height().lengthMm());
        BigInteger width = normalizeSize(pack.outerDimensions().width().lengthMm());
        BigInteger length = normalizeSize(pack.outerDimensions().length().lengthMm());

        BigInteger multResult = height
                .multiply(width)
                .multiply(length);

        BigDecimal result = new BigDecimal(multResult)
                .divide(BigDecimal.valueOf(1_000_000_000))
                .setScale(4, RoundingMode.HALF_UP);

        return new Volume(result);
    }

    private BigInteger normalizeSize(BigInteger size) {
        return size
                .add(BigInteger.valueOf(49))
                .divide(BigInteger.valueOf(50))
                .multiply(BigInteger.valueOf(50));
    }
}
