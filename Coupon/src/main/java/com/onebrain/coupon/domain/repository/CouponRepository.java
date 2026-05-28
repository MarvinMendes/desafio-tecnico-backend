package com.onebrain.coupon.domain.repository;

import com.onebrain.coupon.infrastructure.entity.Coupon;

import java.util.Optional;

public interface CouponRepository {
    Coupon save(Coupon coupon);
    Optional<Coupon> findById(Long id);
    void delete(Long id);
}
