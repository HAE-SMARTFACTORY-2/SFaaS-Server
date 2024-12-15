package org.hae.sfaas.domain.welder.dto.response;

import java.util.Map;

public record WelderFilterGroupResponse(
        String filterGroup,
        Map<String, Object> chartData
) {
}