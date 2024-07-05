package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PointCoordinate(
    @Schema(description = "ширина пункта, градусы", example = "73.398660")
    @NotNull
    BigDecimal latitude,

    @Schema(description = "долгота пункта, градусы", example = "55.027532")
    @NotNull
    BigDecimal longitude) {
}
