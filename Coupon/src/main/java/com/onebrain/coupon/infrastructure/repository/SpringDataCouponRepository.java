package com.onebrain.coupon.infrastructure.repository;

import com.onebrain.coupon.infrastructure.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCouponRepository extends JpaRepository<Coupon, Long> {
}
