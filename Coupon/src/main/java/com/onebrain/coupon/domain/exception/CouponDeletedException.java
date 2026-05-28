package com.onebrain.coupon.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CouponDeletedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CouponDeletedException(String message) {
        super(message);
    }
}
