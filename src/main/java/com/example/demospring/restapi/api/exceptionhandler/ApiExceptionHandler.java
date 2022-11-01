package com.example.demospring.restapi.api.exceptionhandler;

import com.example.demospring.restapi.domain.exception.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ApiError.Field> fields = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            String name = ((FieldError) objectError).getField();
            return new ApiError.Field(name, message);
        }).toList();
        String message = "One or more fields are invalid.";
        ApiError apiError = new ApiError(status.value(), message, OffsetDateTime.now(), fields);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String message = "The request body is invalid.";
        ApiError apiError = new ApiError(status.value(), message, OffsetDateTime.now());
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(httpStatus.value(), ex.getMessage(), OffsetDateTime.now());
        return super.handleExceptionInternal(ex, apiError, new HttpHeaders(), httpStatus, request);
    }

}
