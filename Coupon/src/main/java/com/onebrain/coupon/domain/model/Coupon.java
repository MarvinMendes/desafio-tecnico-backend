package com.onebrain.coupon.domain.model;

import com.onebrain.coupon.domain.exception.CodeMAXSizeException;
import com.onebrain.coupon.domain.exception.DiscountValidException;
import com.onebrain.coupon.domain.valueobject.Code;
import com.onebrain.coupon.domain.valueobject.DiscountValue;
import com.onebrain.coupon.domain.exception.CouponDeletedException;
import com.onebrain.coupon.domain.exception.ExpirationDateException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
public class Coupon {
    private Long id;
    private Code code;
    private String description;
    private DiscountValue discountValue;
    private LocalDate expirationDate;
    private boolean enable;

    public Coupon(Long id, Code code, String description, DiscountValue discountValue, LocalDate expirationDate, boolean enable) {
        this.id = id;
        this.code = Objects.requireNonNull(code);
        this.description = description;
        this.discountValue = Objects.requireNonNull(discountValue);
        this.expirationDate = Objects.requireNonNull(expirationDate);
        this.enable = enable;
        validateInvariants();
    }

    public Coupon(Code code, String description, DiscountValue discountValue, LocalDate expirationDate) {
        this(null, code, description, discountValue, expirationDate, true);
    }

    private void validateInvariants() {
        if (this.expirationDate.isBefore(LocalDate.now())) {
            throw new ExpirationDateException("A data informada não pode estar no passado.");
        }
    }

    public void isEnable() {
        if (!this.enable) {
            throw new CouponDeletedException("O coupon informado já está deletado.");
        }
    }

    public void isValidDate() {
        if (this.expirationDate.isBefore(LocalDate.now())) {
            throw new ExpirationDateException("A data informada não pode estar no passado.");
        }
    }

    public void isValidDiscount() {
        if (this.discountValue.value().compareTo(java.math.BigDecimal.valueOf(0.5)) < 0) {
            throw new DiscountValidException("O Desconto informado deve ser maior que 0.5");
        }
    }

    public void validateCode() {
        String codeWithoutSpecialChar = this.code.toString().replaceAll("[^a-zA-Z0-9]", "");
        if (codeWithoutSpecialChar.length() != 6) {
            throw new CodeMAXSizeException("O código informado deve ter exatamente 6 caracteres.");
        }
    }

    public boolean isEnableFlag() { return enable; }

    public void disable() { this.enable = false; }
    public void updateDescription(String description) { this.description = description; }
}
