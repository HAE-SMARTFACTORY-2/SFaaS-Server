package org.hae.sfaas.domain.welder.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.welder.dto.response.WeldGateTimeInfoResponse;
import org.hae.sfaas.domain.welder.mapper.WelderMapper;
import org.hae.sfaas.domain.welder.model.Welder;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WelderService {

    private final UserMapper userMapper;
    private final WelderMapper welderMapper;

    public List<WeldGateTimeInfoResponse> getSpeedInfo(Long userId, LocalDate startAt, LocalDate endAt, String filter) {
        User user = userMapper.findById(userId);
        //TODO - userValidator 재사용 하기
        if(user == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        // TODO - user가 ADMIN인 경우 전체 factory_id 없이 조회 ? -> 구조 고민 !!

        // 아닌 경우
        Long factoryId = user.getFactoryId();

        // 조회
        List<Welder> welders = null;
        welders = welderMapper.findGateTimeAVGBySpeed(factoryId, startAt, endAt, filter);

        //TODO - 그래프 모양 확정 후 InfoResponse 형태로 제작
        return null;
    }

}
