package com.onebrain.coupon.domain.valueobject;

import com.onebrain.coupon.domain.exception.CodeMAXSizeException;
import java.util.Objects;

public record Code(String value) {
    public Code {
        if (Objects.isNull(value)) {
            throw new CodeMAXSizeException("Código não pode ser nulo.");
        }
        String normalized = value.replaceAll("[^a-zA-Z0-9]", "");
        if (normalized.length() != 6) {
            throw new CodeMAXSizeException("O código informado deve ter exatamente 6 caracteres alfanuméricos.");
        }
        value = normalized.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
