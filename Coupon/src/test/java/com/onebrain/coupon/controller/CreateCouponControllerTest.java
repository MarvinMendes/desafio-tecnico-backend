package com.onebrain.coupon.controller;

import com.onebrain.coupon.domain.dto.CouponInput;
import com.onebrain.coupon.service.CouponService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CreateCouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CouponService couponService;


    @Test
    @DisplayName("Deve criar um cupom com sucesso e retornar 201 Created")
    void shouldCreateCouponWithSuccess() throws Exception {
        CouponInput input = CouponInput.builder()
                .code("PROMO6")
                .description("Desconto de Boas-vindas")
                .discountValue(new BigDecimal("10.0"))
                .expirationDate(LocalDate.now().plusDays(30))
                .build();

        mockMvc.perform(post("/coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request quando o JSON estiver inválido")
    void shouldReturnBadRequestWhenInputIsInvalid() throws Exception {
        CouponInput invalidInput = CouponInput.builder()
                .code("ABC")
                .description("")
                .discountValue(new BigDecimal("0.1"))
                .expirationDate(LocalDate.now().minusDays(1))
                .build();

        mockMvc.perform(post("/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidInput)))
                .andExpect(status().isBadRequest());
    }


}