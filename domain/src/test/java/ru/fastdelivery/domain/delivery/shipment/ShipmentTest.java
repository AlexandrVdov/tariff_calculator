package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimensions.Length;
import ru.fastdelivery.domain.common.dimensions.OuterDimensions;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    @Test
    void whenSummarizingWeightOfAllPackages_thenReturnSum() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);

        var packages = List.of(new Pack(weight1), new Pack(weight2));
        var shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"));

        var massOfShipment = shipment.weightAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
    }

    @Test
    void whenSummarizingVolumeOfAllPackages_thenReturnSum() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);

        var length = new Length(BigInteger.valueOf(100));
        var length2 = new Length(BigInteger.valueOf(200));

        var outerDimensions1 = new OuterDimensions(length, length, length);
        var outerDimensions2 = new OuterDimensions(length2, length2, length2);

        var packages = List.of(new Pack(weight1, outerDimensions1), new Pack(weight2, outerDimensions2));
        var shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"));

        var volumeOfShipment = shipment.volumeAllPackages();

        assertThat(volumeOfShipment.volumeCubM()).isEqualByComparingTo(BigDecimal.valueOf(0.009));
    }
}