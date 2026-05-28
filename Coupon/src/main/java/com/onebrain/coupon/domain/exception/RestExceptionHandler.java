package com.onebrain.coupon.domain.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ExpirationDateException.class)
    public ResponseEntity<ErrorResponse> handleExpirationDate(ExpirationDateException ex) {
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(CouponDeletedException.class)
    public ResponseEntity<ErrorResponse> handleCouponDeleted(CouponDeletedException ex) {
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_MODIFIED.value());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(erro);
    }

    @ExceptionHandler(DiscountValidException.class)
    public ResponseEntity<ErrorResponse> handleDiscountValid(DiscountValidException ex) {
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(CodeMAXSizeException.class)
    public ResponseEntity<ErrorResponse> handleCodeMAXSize(CodeMAXSizeException ex) {
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCouponNotFound(CouponNotFoundException ex) {
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}


