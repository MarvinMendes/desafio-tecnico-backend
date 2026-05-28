package com.onebrain.coupon.service;

import com.onebrain.coupon.domain.exception.CouponNotFoundException;
import com.onebrain.coupon.domain.model.Coupon;
import com.onebrain.coupon.domain.repository.CouponRepository;
import com.onebrain.coupon.domain.valueobject.Code;
import com.onebrain.coupon.domain.valueobject.DiscountValue;
import com.onebrain.coupon.service.impl.CouponServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.onebrain.coupon.infrastructure.entity.Coupon.fromDomain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CouponServiceImplTest {

    private CouponRepository domainRepo;
    private CouponServiceImpl service;

    @BeforeEach
    void setUp() {
        domainRepo = Mockito.mock(CouponRepository.class);
        service = new CouponServiceImpl(domainRepo);
    }

    @Test
    void createShouldValidateAndReturnEntity() {
        Coupon domainCoupon = new Coupon(new Code("PROMO1"), "desc", new DiscountValue(new BigDecimal("1.0")), LocalDate.now().plusDays(5));
        when(domainRepo.save(fromDomain(domainCoupon))).thenReturn(fromDomain(domainCoupon));

        var result = service.create(domainCoupon);

        assertNotNull(result);
        assertEquals(domainCoupon.getDescription(), result.getDescription());
        verify(domainRepo, times(1)).save(fromDomain(domainCoupon));
    }


    @Test
    void deleteShouldThrowWhenNotFound() {
        Long id = 2L;
        when(domainRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(CouponNotFoundException.class, () -> service.delete(id));
    }
}
