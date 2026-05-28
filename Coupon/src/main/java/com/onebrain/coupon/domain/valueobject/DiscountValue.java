package com.onebrain.coupon.domain.valueobject;

import com.onebrain.coupon.domain.exception.DiscountValidException;

import java.math.BigDecimal;
import java.util.Objects;

public record DiscountValue(BigDecimal value) {
    public DiscountValue {
        if (Objects.isNull(value)) {
            throw new DiscountValidException("Desconto não pode ser nulo.");
        }
        if (value.compareTo(BigDecimal.valueOf(0.5)) < 0) {
            throw new DiscountValidException("O Desconto informado deve ser maior que 0.5");
        }
    }
}
