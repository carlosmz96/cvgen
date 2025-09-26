package dev.carlosmz.cvgen.api.cvgenapi.errors;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException; // <-- VALIDACIÓN CORRECTA
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    // VALIDACIÓN de parámetros (path/query)
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

    // Violaciones de integridad en BD (ej. unique)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError(HttpStatus.CONFLICT.getReasonPhrase());
        err.setMessage("Data integrity violation");
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
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

    // 404 por endpoint inexistente
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandler(
            NoHandlerFoundException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        err.setMessage("Recurso no encontrado");
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // 404 por recursos estáticos (incluida UI de swagger si la ruta no existe)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResource(
            NoResourceFoundException ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        err.setMessage("Recurso no encontrado");
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // (Opcional) Fallback final: mejor registrar/loggear y no exponer detalles en prod
    // Elimínalo si prefieres no atrapar todo.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex, HttpServletRequest request) {
        ApiError err = new ApiError();
        err.setTimestamp(OffsetDateTime.now());
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        err.setMessage("Unexpected error");
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
