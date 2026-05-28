package com.onebrain.coupon.service;

import static org.junit.jupiter.api.Assertions.*;

import com.onebrain.coupon.domain.exception.CodeMAXSizeException;
import com.onebrain.coupon.domain.exception.DiscountValidException;
import com.onebrain.coupon.domain.exception.ExpirationDateException;
import com.onebrain.coupon.domain.model.Coupon;
import com.onebrain.coupon.domain.valueobject.Code;
import com.onebrain.coupon.domain.valueobject.DiscountValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class CouponUseCaseMockitoTest {

    @Test
    @DisplayName("Sucesso: Deve validar código alfanumérico corretamente")
    void shouldValidateCodeSuccess() {
        Coupon coupon = new Coupon(
                new Code("PR@MO10"),
                "Descrição",
                new DiscountValue(new BigDecimal("1.0")),
                LocalDate.now().plusDays(10)
        );

        assertDoesNotThrow(() -> coupon.validateCode());
    }

    @Test
    @DisplayName("Erro: Deve falhar quando o código limpo for menor que 6")
    void shouldFailWhenCodeShort() {
        assertThrows(CodeMAXSizeException.class, () -> new Code("A#1"));
    }

    @Test
    @DisplayName("Erro: Deve falhar quando o desconto for menor que 0.5 (validação no Value Object)")
    void shouldFailWhenDiscountInvalid() {
        assertThrows(DiscountValidException.class, () -> new DiscountValue(new BigDecimal("0.1")));
    }

    @Test
    @DisplayName("Erro: Deve falhar quando a data for no passado (validação no agregado)")
    void shouldFailWhenDateInPast() {
        assertThrows(ExpirationDateException.class, () -> new Coupon(
                new Code("PRMO11"),
                "Descrição",
                new DiscountValue(new BigDecimal("1.0")),
                LocalDate.now().minusDays(5)
        ));
    }

    @Test
    @DisplayName("Sucesso: Validação de data futura")
    void shouldValidateDateSuccess() {
        Coupon coupon = new Coupon(
                new Code("PRMO22"),
                "Descrição",
                new DiscountValue(new BigDecimal("1.0")),
                LocalDate.now().plusDays(1)
        );

        assertDoesNotThrow(() -> coupon.isValidDate());
    }
}
