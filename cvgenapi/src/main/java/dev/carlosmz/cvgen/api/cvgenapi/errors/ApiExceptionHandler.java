package dev.carlosmz.cvgen.api.cvgenapi.errors;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> {
                    FieldErrorItem item = new FieldErrorItem();
                    item.setField(fe.getField());
                    item.setRejectedValue(fe.getRejectedValue());
                    item.setMessage(fe.getDefaultMessage());
                    return item;
                })
                .collect(Collectors.toList());

        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        err.setMessage("Validation failed");
        err.setPath(request.getRequestURI());
        err.setValidationErrors(fieldErrors);

        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            EntityNotFoundException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        err.setMessage("Type mismatch: " + ex.getName());
        err.setPath(request.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        err.setMessage("Malformed JSON request");
        err.setPath(request.getRequestURI());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

}
