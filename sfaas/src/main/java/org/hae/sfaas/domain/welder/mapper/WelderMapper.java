package org.hae.sfaas.domain.welder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.welder.model.Status;
import org.hae.sfaas.domain.welder.model.Welder;
import org.hae.sfaas.domain.welder.model.WelderGateTime;

import java.time.LocalDate;

import java.util.List;

@Mapper
public interface WelderMapper {
    List<WelderGateTime> findGateTimeAVGBySpeed(Long factoryId, LocalDate startAt, LocalDate endAt, String filter);

    List<Welder> findAllByfactoryId(Long factoryId, LocalDate startAt, LocalDate endAt, Status status);
}
