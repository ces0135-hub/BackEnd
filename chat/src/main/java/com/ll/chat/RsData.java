package com.ll.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// Result Data 의미
public class RsData {
    private String resultCode;
    private String msg;
    private Object data;
}
