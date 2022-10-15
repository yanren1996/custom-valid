package com.example.customvalid.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 用於 restful api 的標準響應
 */
@Getter
@Setter
public class StandardJSend {

    private String status;

    private Object data;

    private String message;

}
