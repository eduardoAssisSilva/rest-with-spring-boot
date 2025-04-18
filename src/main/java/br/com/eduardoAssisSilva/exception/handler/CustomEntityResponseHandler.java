package br.com.eduardoAssisSilva.exception.handler;

import br.com.eduardoAssisSilva.exception.ExceptionResponse;
import br.com.eduardoAssisSilva.exception.ResourceNotFoundException;
import br.com.eduardoAssisSilva.exception.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request){
        return buildResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request){
        return buildResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request){
        return buildResponse(ex, request, HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<ExceptionResponse> buildResponse(Exception ex, WebRequest request, HttpStatus status) {
        ExceptionResponse response = ExceptionResponse.of(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, status);
    }
}
