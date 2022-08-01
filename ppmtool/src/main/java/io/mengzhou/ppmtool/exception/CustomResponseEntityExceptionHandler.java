package io.mengzhou.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exception, WebRequest request) {
        ProjectIDExceptionResponse projectIDExceptionResponse = new ProjectIDExceptionResponse(exception.getMessage());
        return new ResponseEntity(projectIDExceptionResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception, WebRequest request) {
        ProjectNotFoundExceptionResponse response = new ProjectNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameRepeatException(UsernameRepeatException exception, WebRequest request) {
        UsernameRepeatExceptionResponse response = new UsernameRepeatExceptionResponse(exception.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
