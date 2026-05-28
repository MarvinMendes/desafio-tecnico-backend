package com.onebrain.coupon.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CodeMINSizeException extends RuntimeException {
    public CodeMINSizeException(String message) {
    }
}
