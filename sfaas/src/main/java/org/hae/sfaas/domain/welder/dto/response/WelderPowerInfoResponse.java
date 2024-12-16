package org.hae.sfaas.domain.welder.dto.response;

import org.hae.sfaas.domain.welder.model.WelderPower;

public record WelderPowerInfoResponse(
        Double minPower,
        Double maxPower,
        Double realPower
) {
    public static WelderPowerInfoResponse of(WelderPower welderPower) {
        return new WelderPowerInfoResponse(
                welderPower.getSetPower() * 20 - 70,
                welderPower.getSetPower() * 20 + 70,
                welderPower.getRealPower()
        );
    }
}
