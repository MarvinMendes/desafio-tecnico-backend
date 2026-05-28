package com.onebrain.coupon.service;

import com.onebrain.coupon.domain.model.Coupon;

public interface CouponService {
    com.onebrain.coupon.infrastructure.entity.Coupon create(Coupon coupon);

    void delete(Long id);
}
