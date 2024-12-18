package org.hae.sfaas.domain.batteryPack.dto.response;

import org.hae.sfaas.domain.batteryPack.model.BatteryPackDetail;
import org.hae.sfaas.domain.welder.model.Status;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public record BatteryPackDetailResponse (
        Double rsocMin,
        Double rsocMax,
        Double rsocAvg,
        Double usocMin,
        Double usocMax,
        Double usocAvg,
        Double chgPmax,
        Double dchgPmax,
        Double chgImax,
        Double dchgImax,
        Double cellVoltageMin,
        Double cellVoltageMax,
        Double cellVoltageDv,
        Double cellTempMax,
        Double cellTempMin,
        Double cellTempAvg,
        Status status,
        String factoryCode,
        String factoryLocation,
        LocalDateTime createAt
) {
    public static BatteryPackDetailResponse of(BatteryPackDetail batteryPackDetail) {
        return new BatteryPackDetailResponse(
                roundToTwoDecimalPlaces(batteryPackDetail.getRsocMin()),
                roundToTwoDecimalPlaces(batteryPackDetail.getRsocMax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getRsocAvg()),
                roundToTwoDecimalPlaces(batteryPackDetail.getUsocMin()),
                roundToTwoDecimalPlaces(batteryPackDetail.getUsocMax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getUsocAvg()),
                roundToTwoDecimalPlaces(batteryPackDetail.getChgPmax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getDchgPmax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getChgImax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getDchgImax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getCellVoltageMin()),
                roundToTwoDecimalPlaces(batteryPackDetail.getCellVoltageMax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getCellVoltageDv()),
                roundToTwoDecimalPlaces(batteryPackDetail.getCellTempMax()),
                roundToTwoDecimalPlaces(batteryPackDetail.getCellTempMin()),
                roundToTwoDecimalPlaces(batteryPackDetail.getCellTempAvg()),
                batteryPackDetail.getStatus(),
                batteryPackDetail.getFactoryCode(),
                batteryPackDetail.getFactoryLocation(),
                batteryPackDetail.getCreateAt()
        );
    }

    private static Double roundToTwoDecimalPlaces(Double value) {
        if (value == null) {
            return null;
        }
        // Round to 2 decimal places using BigDecimal
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
