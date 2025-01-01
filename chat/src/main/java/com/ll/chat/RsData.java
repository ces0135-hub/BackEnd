package com.ll.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// Result Data 의미
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;
}
