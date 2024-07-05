package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Данные для расчета стоимости доставки")
public record CalculatePackagesRequest(
        @Schema(description = "Список упаковок отправления",
                example = "[{\"weight\": 4056,\"length\": 345,\"width\": 589,\"height\": 234}]")
        @NotNull
        @NotEmpty
        List<CargoPackage> packages,

        @Schema(description = "Трехбуквенный код валюты", example = "RUB")
        @NotNull
        String currencyCode,

        @Schema(description = "Координаты пункта прибытия",
                example = "{\"latitude\" : 73.398660,\"longitude\" : 55.027532}")
        @Valid
        PointCoordinate destination,

        @Schema(description = "Координаты пункта отправления",
                example = "{\"latitude\" : 55.44600,\"longitude\" : 65.339151}")
        @Valid
        PointCoordinate departure
) {
        public CalculatePackagesRequest(List<CargoPackage> packages, String currencyCode) {
                this(packages, currencyCode, null, null);
        }
}
