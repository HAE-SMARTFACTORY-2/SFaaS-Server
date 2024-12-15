package org.hae.sfaas.domain.welder.dto.response;

import java.util.List;

public record WelderFilterGroupResponse(
        String filterGroup,
        List<WeldGateTimeInfoResponse> chartData
) {
}