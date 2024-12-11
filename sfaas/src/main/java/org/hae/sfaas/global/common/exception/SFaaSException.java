package org.hae.sfaas.global.common.exception;

import lombok.Getter;
import org.hae.sfaas.global.common.response.ErrorType;

@Getter
public class SFaaSException extends RuntimeException {

    private final ErrorType errorType;

    public SFaaSException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public int getHttpStatus() {
        return errorType.getHttpStatusCode();
    }
}