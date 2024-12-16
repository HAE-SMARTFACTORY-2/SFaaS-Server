package org.hae.sfaas.domain.aging.dto.response;

import org.hae.sfaas.domain.aging.model.AgingTempInfo;

public record AgingTempInfoResponse(
        Long temp
) {
    public static AgingTempInfoResponse of(AgingTempInfo agingTempInfo){
        return new AgingTempInfoResponse(
                agingTempInfo.getTemp()
        );
    }
}
