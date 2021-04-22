package com.github.actar233.mbdpay.exception;

public class MbdPayException extends Exception {

    public MbdPayException() {
    }

    public MbdPayException(String message) {
        super(message);
    }

    public MbdPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public MbdPayException(Throwable cause) {
        super(cause);
    }

    public MbdPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
