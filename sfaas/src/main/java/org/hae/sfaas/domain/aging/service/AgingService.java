package org.hae.sfaas.domain.aging.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.aging.dto.response.AgingFilterGroupResponse;
import org.hae.sfaas.domain.aging.dto.response.AgingStatusResponse;
import org.hae.sfaas.domain.aging.dto.response.AgingTempInfoResponse;
import org.hae.sfaas.domain.aging.mapper.AgingMapper;
import org.hae.sfaas.domain.aging.model.AgingStatus;
import org.hae.sfaas.domain.aging.model.AgingTempInfo;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AgingService {
    private final UserMapper userMapper;
    private final AgingMapper agingMapper;

    public List<AgingFilterGroupResponse> getTempInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<AgingTempInfo> tempInfos = agingMapper.findTempInfos(fId,startAt,endAt,filter);
        return tempInfos.stream()
                .collect(Collectors.groupingBy(
                        AgingTempInfo::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(AgingTempInfoResponse::of,Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> new AgingFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }

    public List<AgingFilterGroupResponse> getStatusInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<AgingStatus> agingStatuses = agingMapper.findStatusCount(fId,startAt,endAt,filter);

        return agingStatuses.stream()
                .collect(Collectors.groupingBy(
                        AgingStatus::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(AgingStatusResponse::of,Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> new AgingFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }
}
