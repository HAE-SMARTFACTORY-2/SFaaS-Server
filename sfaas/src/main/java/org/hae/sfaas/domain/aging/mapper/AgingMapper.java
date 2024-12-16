package org.hae.sfaas.domain.aging.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.aging.model.AgingStatus;
import org.hae.sfaas.domain.aging.model.AgingTempInfo;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AgingMapper {
    List<AgingTempInfo> findTempInfos(Long factoryId, LocalDate startAt,LocalDate endAt,String filter);
    List<AgingStatus> findStatusCount(Long factoryId, LocalDate startAt,LocalDate endAt,String filter);
}
