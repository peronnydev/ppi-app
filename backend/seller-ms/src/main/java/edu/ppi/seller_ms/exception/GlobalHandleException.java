package edu.ppi.seller_ms.exception;

import edu.ppi.seller_ms.dto.ErrorResponseDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {
    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errorsMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldError = (e instanceof FieldError field) ? field.getField() : e.getObjectName();
            errorsMap.put(fieldError, e.getDefaultMessage());
        });
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ErrorResponseDTO> handleSellerException(SellerException ex, WebRequest raq){
        return buildErrorResponse(ex.getMessage(), raq);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductException(ProductException ex, WebRequest raq){
        return buildErrorResponse(ex.getMessage(), raq);
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(String message, WebRequest raq){
        var errorResponse = ErrorResponseDTO.builder()
                .apiPath(raq.getDescription(false))
                .errorTime(LocalDateTime.now())
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(message)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
