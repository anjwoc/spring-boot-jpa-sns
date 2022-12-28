package me.anjwoc.sns.common.exception;

public class APIException extends RuntimeException {
    private APIErrorCode errorCode;
    private String message;

    public APIException(APIErrorCode errorCode, String key, String value) {
        this.errorCode = errorCode;
        this.message = String.format("%s is %s", key, value);
    }
}
