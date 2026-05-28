package com.onebrain.coupon.infrastructure.persistence;


import com.onebrain.coupon.domain.repository.CouponRepository;
import com.onebrain.coupon.infrastructure.entity.Coupon;
import com.onebrain.coupon.infrastructure.repository.SpringDataCouponRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class JpaCouponRepositoryAdapter implements CouponRepository {

    private final SpringDataCouponRepository jpaRepository;

    public JpaCouponRepositoryAdapter(SpringDataCouponRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Coupon save(Coupon coupon) {
        return jpaRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> findById(Long id) {
        return jpaRepository.findById(id);
    }


    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}