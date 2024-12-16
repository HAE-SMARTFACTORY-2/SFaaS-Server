package org.hae.sfaas.domain.battery.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.battery.model.BatteryStatus;

@Mapper
public interface BatteryMapper {

    BatteryStatus findStatusCountbyfId(Long userId, Long factoryId);
}
