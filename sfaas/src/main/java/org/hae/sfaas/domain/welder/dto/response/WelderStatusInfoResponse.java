package org.hae.sfaas.domain.welder.dto.response;

import org.hae.sfaas.domain.welder.model.WelderStatus;

public record WelderStatusInfoResponse(
        int totalCount,
        int okCount,
        int ngCount,
        String filterGroup
) {
    public static WelderStatusInfoResponse of(WelderStatus welderStatus) {
        return new WelderStatusInfoResponse(
                welderStatus.getTotalCount(),
                welderStatus.getOkCount(),
                welderStatus.getNgCount(),
                welderStatus.getFilterGroup()
        );
    }
}
