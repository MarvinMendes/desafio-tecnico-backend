package com.onebrain.coupon.controller;

import com.onebrain.coupon.domain.model.Coupon;
import com.onebrain.coupon.domain.dto.CouponInput;
import com.onebrain.coupon.domain.valueobject.Code;
import com.onebrain.coupon.domain.valueobject.DiscountValue;
import com.onebrain.coupon.service.CouponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo cupom", description = "Registra um cupom de desconto no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupom criado com sucesso"),
            @ApiResponse(responseCode = "400"),
    })
    public com.onebrain.coupon.infrastructure.entity.Coupon create(@RequestBody @Valid CouponInput couponInput) {
        Coupon coupon = Coupon.builder()
                .discountValue(new DiscountValue(couponInput.getDiscountValue()))
                .code(new Code(couponInput.getCode()))
                .description(couponInput.getDescription())
                .expirationDate(couponInput.getExpirationDate())
                .build();

        return couponService.create(coupon);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Coupon", description = "Faz a deleção lógica de um cupom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupom deletado com sucesso"),
            @ApiResponse(responseCode = "304", description = "Não modifica o dado ele já tendo sido removido."),
            @ApiResponse(responseCode = "404", description = "Caso seja informado um ID inexistente na base de dados."),
    })
    public void delete(@PathVariable Long id) {
        couponService.delete(id);
    }



}
