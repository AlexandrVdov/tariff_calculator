package ru.fastdelivery.domain.delivery.point;

/**
 * Максимальные и минимальные возможные значения широт
 */
public interface CoordinatePropertiesProvider {

    /**
     * @param degree градус ширины пункта
     * @return входит ли ширина в допустимый диапозон доставки
     */
    boolean isInRangeLatitude(Degree degree);

    /**
     * @param degree градус долготы пункта
     * @return входит ли долгота в допустимый диапозон доставки
     */
    boolean isInRangeLongitude(Degree degree);
}
