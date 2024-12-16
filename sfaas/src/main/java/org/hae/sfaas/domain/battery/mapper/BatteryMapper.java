package org.hae.sfaas.domain.battery.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.battery.model.BatteryOutput;
import org.hae.sfaas.domain.battery.model.BatteryStatus;

import java.util.List;

@Mapper
public interface BatteryMapper {

    BatteryStatus findStatusCountbyfId(Long userId, Long factoryId);

    List<BatteryOutput> findBatteryOutput(Long userId, Long factoryId);
}
