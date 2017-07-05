package de.cosmicit.pms.controller;

import de.cosmicit.pms.common.ErrorResponse;
import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.LogicalException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class PMSControllerAdvice extends ResponseEntityExceptionHandler {

    @Value("${portfolio-management-service.debug}")
    private Boolean isDebug = false;

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleControllerException(HttpServletRequest request, Throwable ex) {
        return getResponseEntity(getMessage(ex), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = LogicalException.class)
    public ResponseEntity<Object> handleLogicalException(HttpServletRequest request, LogicalException ex) {
        return getResponseEntity(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Object> handleInvalidParameterException(HttpServletRequest request, InvalidParameterException ex) {
        return getResponseEntity(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        return getResponseEntity(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
        return getResponseEntity("Entity not found", request, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        return getResponseEntity("Resource not found", ((ServletWebRequest) request).getRequest(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return getResponseEntity(getMessage(ex), ((ServletWebRequest) request).getRequest(), status);
    }

    protected ResponseEntity<Object> getResponseEntity(String errorMessage, HttpServletRequest request, HttpStatus status) {
        ErrorResponse errorResponse = ErrorResponse.create(status.value(), status.getReasonPhrase(), errorMessage, request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    protected String getMessage(Throwable ex) {
        if (isDebug) {
            return ex.getMessage() + " " + "Details:" + ex.toString();
        } else {
            return "An Error Occured";
        }
    }

    @RequestMapping(value = "/")
    public ResponseEntity<Object> accessBasePath(HttpServletRequest request) {
        return getResponseEntity("Resource not found", request, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/profile")
    public ResponseEntity<Object> accessProfile(HttpServletRequest request) {
        return getResponseEntity("Resource not found", request, HttpStatus.NOT_FOUND);
    }
}
