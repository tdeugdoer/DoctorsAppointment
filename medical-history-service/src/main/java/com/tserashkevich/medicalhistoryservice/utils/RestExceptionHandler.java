package com.tserashkevich.medicalhistoryservice.utils;

import com.tserashkevich.medicalhistoryservice.dtos.ExceptionResponse;
import com.tserashkevich.medicalhistoryservice.dtos.ValidationErrorResponse;
import com.tserashkevich.medicalhistoryservice.dtos.Violation;
import com.tserashkevich.medicalhistoryservice.exceptions.*;
import com.tserashkevich.medicalhistoryservice.exceptions.feign.OtherServiceBadRequestException;
import com.tserashkevich.medicalhistoryservice.exceptions.feign.OtherServiceNotFoundException;
import com.tserashkevich.medicalhistoryservice.exceptions.feign.OtherServiceServerException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler({MedicalRecordNotFoundException.class, AppointmentNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundExceptions(RuntimeException ex) {
        log.error(LogList.NOT_FOUND_ERROR, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(MedicalRecordMissingFileKeyException.class)
    public ResponseEntity<ExceptionResponse> handleMedicalRecordMissingFileKeyException(RuntimeException ex) {
        log.error(LogList.MISSING_FILE_KEY_ERROR, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(BadFileException.class)
    public ResponseEntity<ExceptionResponse> handleBadFileException(RuntimeException ex) {
        log.error(LogList.BAD_FILE, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<ExceptionResponse> handleFileUploadException(RuntimeException ex) {
        log.error(LogList.FILE_PROCESSING_ERROR, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();
        log.error(LogList.METHOD_ARGUMENT_ERROR, violations);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorResponse(violations));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(RuntimeException ex) {
        log.error(LogList.CONSTRAINT_VIOLATION_ERROR, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(LogList.METHOD_ARGUMENT_ERROR, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("Wrong request parameter: " + ex.getName()));
    }

    @ExceptionHandler(OtherServiceBadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleOtherServiceBadRequestException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(OtherServiceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOtherServiceNotFoundException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(OtherServiceServerException.class)
    public ResponseEntity<ExceptionResponse> handleOtherServiceServerException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ex.getMessage()));
    }
}
