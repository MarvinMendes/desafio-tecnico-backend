package com.onebrain.coupon.service.impl;

import com.onebrain.coupon.domain.exception.CouponNotFoundException;

import com.onebrain.coupon.domain.model.Coupon;
import com.onebrain.coupon.domain.repository.CouponRepository;
import com.onebrain.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.onebrain.coupon.infrastructure.entity.Coupon.fromDomain;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public com.onebrain.coupon.infrastructure.entity.Coupon create(Coupon coupon) {
        coupon.isValidDate();
        coupon.isValidDiscount();
        coupon.validateCode();

        return couponRepository.save(fromDomain(coupon));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        couponRepository.findById(id).ifPresentOrElse(
                domainCoupon -> {
                    domainCoupon.isEnable();
                    domainCoupon.disable();
                    couponRepository.save(domainCoupon);
                }, CouponServiceImpl::handleNotFound);

    }

    private static void handleNotFound() {
        throw new CouponNotFoundException("Para o ID informado não foi encontrado um coupon.");
    }
}
