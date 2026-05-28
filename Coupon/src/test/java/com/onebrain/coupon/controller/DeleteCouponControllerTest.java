package com.onebrain.coupon.controller;

import com.onebrain.coupon.domain.model.Coupon;
import com.onebrain.coupon.domain.valueobject.Code;
import com.onebrain.coupon.domain.valueobject.DiscountValue;
import com.onebrain.coupon.infrastructure.repository.SpringDataCouponRepository;
import com.onebrain.coupon.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteCouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringDataCouponRepository springDataCouponRepository;

    @Autowired
    private CouponService couponService;

    private Long existingCouponId;

    @BeforeEach
    void setUp() {
        springDataCouponRepository.deleteAll();

        Coupon domainCoupon = new Coupon(
                new Code("PROMO6"),
                "Descrição",
                new DiscountValue(new BigDecimal("20.00")),
                LocalDate.now().plusDays(30)
        );

        @SuppressWarnings({"rawtypes", "unchecked"})
        Object saved = ((org.springframework.data.repository.CrudRepository) springDataCouponRepository)
                .save(com.onebrain.coupon.infrastructure.entity.Coupon.fromDomain(domainCoupon));

        com.onebrain.coupon.infrastructure.entity.Coupon savedEntity = (com.onebrain.coupon.infrastructure.entity.Coupon) saved;
        this.existingCouponId = savedEntity.getId();
    }

    @Test
    @DisplayName("Should return 200 OK when deleting an existing coupon")
    void shouldReturn200WhenDeletingExistingCoupon() throws Exception {
        mockMvc.perform(delete("/coupon/{id}", existingCouponId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
