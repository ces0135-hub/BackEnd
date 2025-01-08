package com.example.demo.global.rsData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 자주 사용되는 Response Data 형태
@Getter
@Setter
@AllArgsConstructor
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;

    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        // 전달된 인자(resultCode, msg, data)를 사용해 RsData 객체를 생성하고 반환합니다.
        return new RsData<>(resultCode, msg, data);
    }

    public static <T> RsData<T> of(String resultCode, String msg) {
        return of(resultCode, msg, null);
        // data 값 없이 of 메서드를 호출할 수 있도록 제공합니다.
        //내부적으로 첫 번째 of 메서드를 호출하며, data를 null로 설정합니다.
    }

    @JsonIgnore
    public boolean isSuccess() {
        return resultCode.startsWith("200");
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}