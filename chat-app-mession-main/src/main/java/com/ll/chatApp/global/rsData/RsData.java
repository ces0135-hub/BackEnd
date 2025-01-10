package com.ll.chatApp.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private final T data;
    private final int statusCode;

    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        int statusCode = Integer.parseInt(resultCode);

        return new RsData<>(resultCode, msg, data, statusCode);
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 400;
    }

    public boolean isFail() {
        return !isSuccess();
    }
}



// 주로 사용하는 RsData 꼴
//@Getter
//@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
//public class RsData<T> {
//    private String resultCode;
//    private String msg;
//    private T data;
//
//    public static <T> RsData<T> of(String resultCode, String msg, T data) {
//        return new RsData<>(resultCode, msg, data);
//    }
//
//    public static <T> RsData<T> of(String resultCode, String msg) {
//        return of(resultCode, msg, null);
//    }
//
//    public boolean isSuccess() {
//        return resultCode.startsWith("S-");
//    }
//
//    public boolean isFail() {
//        return !isSuccess();
//    }
//
//    public Optional<RsData<T>> optional() {
//        return Optional.of(this);
//    }
//
//    public <T> RsData<T> newDataOf(T data) {
//        return new RsData<T>(getResultCode(), getMsg(), data);
//    }
//}
