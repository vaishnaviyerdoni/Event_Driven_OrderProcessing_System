package com.event.paymentProcessing.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.event.shared_events.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PaymentException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralError(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(new ErrorResponse(500, "Unexpected Error Occured"));
    }
}
