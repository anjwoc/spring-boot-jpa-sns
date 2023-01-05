package me.anjwoc.sns.common.exception;

public class APIException extends RuntimeException {
    private APIErrorCode errorCode;
    private String message;

    public APIException(APIErrorCode errorCode, String key, String value) {
        this.errorCode = errorCode;
        this.message = String.format("%s is %s", key, value);
    }

    public APIException(APIErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }


    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s. %s", errorCode.getMessage(), message);
        }

    }
}
