package com.onebrain.coupon.domain.exception;

import lombok.Data;

@Data
public class ErrorResponse {

        private String mensagem;
        private int codigo;

        public ErrorResponse(String mensagem, int codigo) {
            this.mensagem = mensagem;
            this.codigo = codigo;
        }


}



