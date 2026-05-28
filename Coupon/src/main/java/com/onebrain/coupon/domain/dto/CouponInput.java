package com.onebrain.coupon.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponInput {

    @NotBlank(message = "O código do cupom é obrigatório.")
    @Schema(description = "Código do cupom", example = "VERAO2")
    private String code;

    @NotBlank(message = "A descrição do cupom é obrigatória.")
    @Schema(description = "Descrição detalhada do cupom", example = "Cupom para a promoção de verão")
    private String description;

    @NotNull(message = "O valor de desconto do cupom é obrigatório.")
    @Schema(description = "Valor do desconto", example = "15.50")
    private BigDecimal discountValue;

    @NotNull(message = "A data de expiração do cupom é obrigatória.")
    @Schema(description = "Data de expiração do cupom | A data de expiração não pode ser no passado.", example = "2026-12-31", format = "date")
    private LocalDate expirationDate;

}
