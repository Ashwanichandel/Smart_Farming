package com.cf.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
 
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("Resource not found: {}", ex.getMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
		return (ResponseEntity<ErrorResponse>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
		log.error("Duplicate resource: {}", ex.getMessage());
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());
		return (ResponseEntity<ErrorResponse>) ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	//@ExceptionHandler(MethodArgumentNotValidException.class)
	/*
	 * public ResponseEntity<Map<String, Object>>
	 * handleValidationExceptions(MethodArgumentNotValidException ex) { Map<String,
	 * String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put(fieldName, errorMessage); });
	 * 
	 * Map<String, Object> response = new HashMap<>(); response.put("status",
	 * HttpStatus.BAD_REQUEST.value()); response.put("message",
	 * "Validation failed"); response.put("errors", errors);
	 * response.put("timestamp", LocalDateTime.now());
	 * 
	 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); }
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
		log.error("Internal server error: {}", ex.getMessage(), ex);
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"An unexpected error occurred: " + ex.getMessage(), LocalDateTime.now());
		return (ResponseEntity<ErrorResponse>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}

class ErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timestamp;

	public ErrorResponse(int status, String message, LocalDateTime timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	// Getters and setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
