package wiks.bikesharing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class, BadRequestException.class})
    public ResponseEntity<Object> handleException(RuntimeException exc) {
        if (exc instanceof NotFoundException) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(exc.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        } else if (exc instanceof BadRequestException) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(exc.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
        ExceptionResponse exception = new ExceptionResponse(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis());
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}