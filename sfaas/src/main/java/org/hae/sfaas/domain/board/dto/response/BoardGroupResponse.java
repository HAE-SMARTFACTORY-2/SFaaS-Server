package org.hae.sfaas.domain.board.dto.response;

import java.util.List;

public record BoardGroupResponse<T>(
        List<T> Data
) {
}
