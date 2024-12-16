package org.hae.sfaas.domain.batteryPack.dto.response;

import org.hae.sfaas.domain.batteryPack.model.BatteryPackDetail;
import org.hae.sfaas.domain.welder.model.Status;

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
){
    public static BatteryPackDetailResponse of(BatteryPackDetail batteryPackDetail){
        return new BatteryPackDetailResponse(
              batteryPackDetail.getRsocMin(),
              batteryPackDetail.getRsocMax(),
              batteryPackDetail.getRsocAvg(),
              batteryPackDetail.getUsocMin(),
              batteryPackDetail.getUsocMax(),
              batteryPackDetail.getUsocAvg(),
              batteryPackDetail.getChgPmax(),
              batteryPackDetail.getDchgPmax(),
              batteryPackDetail.getChgImax(),
              batteryPackDetail.getDchgImax(),
              batteryPackDetail.getCellVoltageMin(),
              batteryPackDetail.getCellVoltageMax(),
              batteryPackDetail.getCellVoltageDv(),
              batteryPackDetail.getCellTempMax(),
              batteryPackDetail.getCellTempMin(),
              batteryPackDetail.getCellTempAvg(),
              batteryPackDetail.getStatus(),
                batteryPackDetail.getFactoryCode(),
                batteryPackDetail.getFactoryLocation(),
              batteryPackDetail.getCreateAt()
        );
    }
}
