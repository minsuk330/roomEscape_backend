package com.sekurity.room_escape.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(
      HttpServletRequest request, BusinessException e
  ) {
    ErrorCode errorCode = e.getErrorCode();
    int status = errorCode.equals(ErrorCode.SESSION_EXPIRED) ? 401 : 400;
    return ResponseEntity
        .status(status)
        .body(ErrorResponse.builder()
            .path(request.getRequestURI())
            .code(errorCode.getCode())
            .message(e.getMessage())
            .build()
        );
  }


}
