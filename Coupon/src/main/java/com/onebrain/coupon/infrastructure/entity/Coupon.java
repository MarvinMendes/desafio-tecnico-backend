package com.onebrain.coupon.infrastructure.entity;

import com.onebrain.coupon.domain.valueobject.Code;
import com.onebrain.coupon.domain.valueobject.DiscountValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_COUPON")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    private BigDecimal discountValue;

    private LocalDate expirationDate;

    @Builder.Default
    private Boolean enable = Boolean.TRUE;

    public static Coupon fromDomain(com.onebrain.coupon.domain.model.Coupon domain) {
        Coupon e = new Coupon();
        e.setId(domain.getId());
        e.setCode(domain.getCode().toString());
        e.setDescription(domain.getDescription());
        e.setDiscountValue(domain.getDiscountValue().value());
        e.setExpirationDate(domain.getExpirationDate());
        e.setEnable(domain.isEnableFlag());
        return e;
    }

    public com.onebrain.coupon.domain.model.Coupon toDomain() {
        return new com.onebrain.coupon.domain.model.Coupon(
                this.id,
                new Code(this.code),
                this.description,
                new DiscountValue(this.discountValue),
                this.expirationDate,
                Boolean.TRUE.equals(this.enable)
        );
    }

    public void isEnable() {
        this.toDomain().isEnable();
    }

    public void isValidDate() {
        this.toDomain().isValidDate();
    }

    public void isValidDiscount() {
        this.toDomain().isValidDiscount();
    }

    public void validateCode() {
        this.toDomain().validateCode();
    }

    public void disable() {
       this.toDomain().disable();
    }
}
