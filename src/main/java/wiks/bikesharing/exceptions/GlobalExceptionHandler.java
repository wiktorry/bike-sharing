package wiks.bikesharing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Map<Class<? extends RuntimeException>, HttpStatus> exceptionTypes;

    public GlobalExceptionHandler() {
        exceptionTypes = Map.of(
                NotFoundException.class, HttpStatus.NOT_FOUND,
                BadRequestException.class, HttpStatus.BAD_REQUEST,
                UnauthorizedException.class, HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = {NotFoundException.class, BadRequestException.class, UnauthorizedException.class})
    public ResponseEntity<Object> handleException(RuntimeException exc) {
        if (exceptionTypes.containsKey(exc.getClass())) {
            final HttpStatus httpStatus = exceptionTypes.get(exc.getClass());
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    exc.getMessage(),
                    httpStatus.value(),
                    System.currentTimeMillis()
            );
            return new ResponseEntity<>(exceptionResponse, httpStatus);
        }
        ExceptionResponse exception = new ExceptionResponse(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}