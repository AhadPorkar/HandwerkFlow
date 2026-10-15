package com.ahadporkar.handwerkflow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DataIntegrityViolationException;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) ProblemDetail invalid(MethodArgumentNotValidException ex){var p=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);p.setTitle("Validation failed");p.setDetail(ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).distinct().reduce((a,b)->a+"; "+b).orElse("Invalid input"));return p;}
 @ExceptionHandler(DataIntegrityViolationException.class) ProblemDetail duplicate(DataIntegrityViolationException ex){var p=ProblemDetail.forStatus(HttpStatus.CONFLICT);p.setTitle("Data constraint violation");p.setDetail("Duplicate or related data prevents this operation");return p;}
 @ExceptionHandler(ResponseStatusException.class) ProblemDetail response(ResponseStatusException ex){var p=ProblemDetail.forStatus(ex.getStatusCode());p.setDetail(ex.getReason());return p;}
}
