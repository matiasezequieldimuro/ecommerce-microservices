package mdimuro.ecommerce.microservices.inventory_service.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import mdimuro.ecommerce.microservices.inventory_service.application.errors.ArticleNotFoundException;
import mdimuro.ecommerce.microservices.inventory_service.application.errors.ArticlesSavedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticlesSavedException.class)
    public ResponseEntity<ErrorResponse> handleArticlesSavedException(ArticlesSavedException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(error.getStatusCode()).body(error);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleArticleNotFoundException(ArticleNotFoundException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(error.getStatusCode()).body(error);
    }
}
