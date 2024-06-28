package ru.fastdelivery.domain.common.volume;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VolumeTest {

    @Test
    @DisplayName("Попытка создать отрицательный объем -> исключение")
    void whenVolumeBelowZero_thenException() {
        var volume = new BigDecimal("-0.1");
        assertThatThrownBy(() -> new Volume(volume))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equalsTypeWidth_same() {
        var volume = new Volume(new BigDecimal("0.001"));
        var volumeSame = new Volume(new BigDecimal("0.001"));

        assertThat(volume)
                .isEqualTo(volumeSame)
                .hasSameHashCodeAs(volumeSame);
    }

    @Test
    void equalsNull_false() {
        var volume = new Volume(new BigDecimal("0.4"));

        assertThat(volume).isNotEqualTo(null);
    }

    @Test
    @DisplayName("Добавление положительного объема -> объем увеличился")
    void whenAddPositiveVolume_thenVolumeIsIncreased() {
        var volumeBase = new Volume(new BigDecimal("0.001"));
        var actual = volumeBase.add(new Volume(new BigDecimal("0.001")));

        assertThat(actual)
                .isEqualTo(new Volume(new BigDecimal("0.002")));
    }
}
