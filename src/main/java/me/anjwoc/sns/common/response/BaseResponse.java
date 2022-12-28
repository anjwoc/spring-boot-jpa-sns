package me.anjwoc.sns.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private String code;
    private T data;

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>("success", null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("success", data);
    }

    public static <T> BaseResponse<T> error(String code) {
        return new BaseResponse<>(code, null);
    }

    public String toStream() {
        if (code == null) {
            return "{" +
                    "\"code\":" + "\"" + code + "\"," +
                    "\"data\":" + null +
                    "}";
        }

        return "{" +
                "\"code\":" + "\"" + code + "\"," +
                "\"data\":" + "\"" + data + "\"," +
                "}";
    }
}
