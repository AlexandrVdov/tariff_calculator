package ru.fastdelivery.presentation.calc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimensions.Length;
import ru.fastdelivery.domain.common.dimensions.OuterDimensions;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.point.Coordinate;
import ru.fastdelivery.domain.delivery.point.CoordinateFactory;
import ru.fastdelivery.domain.delivery.point.Degree;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.presentation.api.request.CalculatePackagesRequest;
import ru.fastdelivery.presentation.api.request.CargoPackage;
import ru.fastdelivery.presentation.api.request.PointCoordinate;
import ru.fastdelivery.presentation.api.response.CalculatePackagesResponse;
import ru.fastdelivery.usecase.DistanceCalculateUseCase;
import ru.fastdelivery.usecase.TariffCalculateUseCase;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RestController
@RequestMapping("/api/v1/calculate/")
@RequiredArgsConstructor
@Tag(name = "Расчеты стоимости доставки")
public class CalculateController {
    private final TariffCalculateUseCase tariffCalculateUseCase;
    private final DistanceCalculateUseCase distanceCalculateUseCase;
    private final CurrencyFactory currencyFactory;
    private final CoordinateFactory coordinateFactory;

    @PostMapping
    @Operation(summary = "Расчет стоимости по упаковкам груза")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public CalculatePackagesResponse calculate(
            @Valid @RequestBody CalculatePackagesRequest request) {
        var packs = request.packages().stream()
                .map(this::mapCargoPackageToPack)
                .toList();

        var shipment = new Shipment(packs, currencyFactory.create(request.currencyCode()));
        var calculatedBasicPrice = tariffCalculateUseCase.calc(shipment);
        log.info("calculatedBasicPrice => {}", calculatedBasicPrice);
        var minimalPrice = tariffCalculateUseCase.minimalPrice();

        if (request.destination() == null) {
            return new CalculatePackagesResponse(calculatedBasicPrice, minimalPrice);
        }

        var destination = mapPointCoordinateToCoordinate(request.destination());
        var departure = mapPointCoordinateToCoordinate(request.departure());
        var deliveryDistance = distanceCalculateUseCase.calc(destination, departure);
        var minimalDistance = distanceCalculateUseCase.minimalDistance();
        log.info("deliveryDistance => {}", deliveryDistance);

        if (deliveryDistance.lowerThan(minimalDistance)) {
            return new CalculatePackagesResponse(calculatedBasicPrice, minimalPrice);
        }

        BigDecimal distanceRatio = new BigDecimal(deliveryDistance.distanceKm())
                .divide(new BigDecimal(minimalDistance.distanceKm()), 2, RoundingMode.HALF_UP);
        Price calculatedPrice = calculatedBasicPrice.multiply(distanceRatio);
        log.info("calculatedPrice => {}", calculatedPrice);

        return new CalculatePackagesResponse(calculatedPrice, minimalPrice);
    }

    private Pack mapCargoPackageToPack(CargoPackage cargoPackage) {
        return new Pack(new Weight(cargoPackage.weight()),
                new OuterDimensions(
                new Length(cargoPackage.length()),
                new Length(cargoPackage.width()),
                new Length(cargoPackage.height())));
    }

    private Coordinate mapPointCoordinateToCoordinate(
            PointCoordinate pointCoordinate) {
        return coordinateFactory.create(new Degree(pointCoordinate.latitude()),
                new Degree(pointCoordinate.longitude()));
    }
}

