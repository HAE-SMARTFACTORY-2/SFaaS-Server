package org.hae.sfaas.domain.welder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hae.sfaas.domain.welder.model.Welder;

import java.time.LocalDate;

import java.util.List;

@Mapper
public interface WelderMapper {
    List<Welder> findGateTimeAVGBySpeed(Long factoryId, LocalDate startAt, LocalDate endAt, String filter);
}
