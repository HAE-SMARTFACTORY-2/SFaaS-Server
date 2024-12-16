package org.hae.sfaas.domain.aging.dto.response;

import java.util.List;

public record AgingFilterGroupResponse<T>(
        String filterGroup,
        List<T> chartData
) {
}
