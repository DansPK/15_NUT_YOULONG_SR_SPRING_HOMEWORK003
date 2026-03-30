package org.example._5_nut_youlong_sr_spring_homework003.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundExceptionApiResponse(NotFoundException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequestExceptionApiResponse(BadRequestException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Bad Request");
        problemDetail.setType(URI.create("http://localhost:8080/errors/bad-request"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ProblemDetail> handleConflictExceptionApiResponse(ConflictException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Conflict");
        problemDetail.setType(URI.create(e.getType()));   // ← dynamic now
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request body validation failed");
        problemDetail.setTitle("Validation Failed");
        problemDetail.setType(URI.create("http://localhost:8080/errors/validation-failed"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());

        // Collect field validation errors
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        problemDetail.setProperty("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ProblemDetail> handleMethodValidationException(HandlerMethodValidationException e, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        // Loop through each invalid parameter validation result
        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName();
            for (var messageError : parameterError.getResolvableErrors()) {
                errors.put(paramName, messageError.getDefaultMessage());
            }
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Method parameter validation failed");
        problemDetail.setTitle("Method Parameter Validation Failed");
        problemDetail.setType(URI.create("http://localhost:8080/errors/method-validation-failed"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleMalformedJson(HttpMessageNotReadableException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid JSON in request body. Please check syntax.");
        problemDetail.setTitle("Bad Request");
        problemDetail.setType(URI.create("http://localhost:8080/errors/malformed-json"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

}
