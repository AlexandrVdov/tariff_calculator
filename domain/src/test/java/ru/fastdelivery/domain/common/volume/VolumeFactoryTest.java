package ru.fastdelivery.domain.common.volume;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VolumeFactoryTest {

    @ParameterizedTest(name = "м3 = {arguments} -> объект создан")
    @ValueSource(doubles = { 0.0, 0.1, 0.001, 0.0001 })
    @DisplayName("Значение объема больше 0.0 -> объект не создан")
    void whenVolumeGreaterThanZero_thenObjectCreated(double value) {
        var volume = new Volume(BigDecimal.valueOf(value));

        assertNotNull(volume);
        assertThat(volume.volumeCubM()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

    @ParameterizedTest(name = "m3 = {arguments} -> исключение")
    @ValueSource(doubles = { -0.1, -0.001, -0.0_001 })
    @DisplayName("Значение длины ниже 0.0 -> исключение")
    void whenVolumeLessThanZero_thenThrowException(double value) {
        assertThatThrownBy(() -> new Volume(BigDecimal.valueOf(value)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
