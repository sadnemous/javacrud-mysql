package com.sadnemous.demoSpringBootSvc.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class DemoExceptionHandler {
    @ExceptionHandler(value=NoEmployeeFoundException.class)
    public ResponseEntity <ApiError> handleNoEmployeeFoundException(NoEmployeeFoundException e) {
        if (e.getCause() != null) {
            ApiError apiError = new ApiError(404, e.getMessage(), e.getCause().toString(), 1);
            return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
        } else {
            ApiError apiError = new ApiError(404, e.getMessage(), "Some Bad thing Happened", 1);
            return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
        }
    }
}
