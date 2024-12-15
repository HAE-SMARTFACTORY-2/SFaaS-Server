package org.hae.sfaas.domain.batteryPack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.batteryPack.model.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BatteryPackMapper {
    List<BatteryPackVoltageInfo> findVoltageInfos(Long factoryId, LocalDate startAt, LocalDate endAt, String filter);
    List<BatteryPackTempInfo> findTempInfos(Long factoryId, LocalDate startAt, LocalDate endAt, String filter);
    List<BatteryPackSocInfo> findSocInfos(Long factoryId, LocalDate startAt, LocalDate endAt, String filter);
    List<BatteryPackDetail> findDetailInfos (Long factoryId, LocalDate startAt, LocalDate endAt, String filter);
}
