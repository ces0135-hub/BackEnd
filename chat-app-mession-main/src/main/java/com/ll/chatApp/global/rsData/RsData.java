package com.ll.chatApp.global.rsData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;

    public RsData(String resultCode, String msg) {
        this(resultCode, msg, null);
    }

    @JsonIgnore
    public int getStatusCode() {
        return Integer.parseInt(resultCode.split("-")[0]);
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
