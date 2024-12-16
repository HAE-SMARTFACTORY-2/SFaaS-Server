package org.hae.sfaas.domain.aging.dto.response;

import org.hae.sfaas.domain.aging.model.AgingStatus;

public record AgingStatusResponse (
    int okCount,
    int ngCOunt,
    int totalCount,
    Long temp
){
    public static AgingStatusResponse of(AgingStatus agingStatus){
        return new AgingStatusResponse(
                agingStatus.getOkCount(),
                agingStatus.getNgCount(),
                agingStatus.getOkCount() + agingStatus.getNgCount(),
                agingStatus.getTemp()
        );
    }
}
