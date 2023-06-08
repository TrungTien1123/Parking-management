package vn.com.trungtien.management.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Autowired
    private MessageSource messageSource;

    private String getMessage(String code) {
        return messageSource.getMessage(
                code,
                null,
                "Lỗi server",
                LocaleContextHolder.getLocale());
    }

    // Default exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        String code = "1";
        String message = getMessage("Exception.message");
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Not found url handler
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String code = "2";
        String message = getMessage("NoHandlerFoundException.message")
                + exception.getHttpMethod() + " " + exception.getRequestURL();
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, status);
    }

    private String getMessageFromHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        StringBuilder message = new StringBuilder(exception.getMethod());
        message.append(" ").append(getMessage("HttpRequestMethodNotSupportedException.message"));
        for (HttpMethod method : exception.getSupportedHttpMethods()) {
            message.append(method).append(" ");
        }
        return message.toString();
    }

    // Not support HTTP Method
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String code = "3";
        String message = getMessageFromHttpRequestMethodNotSupportedException(exception);
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, status);
    }

    private String getMessageFromHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        StringBuilder message = new StringBuilder(exception.getContentType() + getMessage("HttpMediaTypeNotSupportedException.message"));
        for (MediaType method : exception.getSupportedMediaTypes()) {
            message.append(method).append(", ");
        }
        return message.substring(0, message.length() - 2);
    }

    // Not support media type
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        String code = "4";
        String message = getMessageFromHttpMediaTypeNotSupportedException(exception);
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, status);
    }

    // BindException: This exception is thrown when fatal binding errors occur.
    // MethodArgumentNotValidException: This exception is thrown when argument
    // annotated with @Valid failed validation:
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String code = "5";
        String message = getMessage("MethodArgumentNotValidException.message");
        String detailMessage = exception.getLocalizedMessage();
        // error
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, status);
    }

    // bean validation error
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        String code = "6";
        String message = getMessage("ConstraintViolationException.message");
        String detailMessage = exception.getLocalizedMessage();
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // MissingServletRequestPartException: This exception is thrown when when the part of a multipart request not found
    // MissingServletRequestParameterException: This exception is thrown when request missing parameter:
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String code = "7";
        String message = exception.getParameterName() + " " + getMessage("MissingServletRequestParameterException.message");
        String detailMessage = exception.getLocalizedMessage();

        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, status);
    }

    // TypeMismatchException: This exception is thrown when try to set bean property with wrong type.
    // MethodArgumentTypeMismatchException: This exception is thrown when method argument is not the expected type:
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        String code = "8";
        String message = exception.getName() + getMessage("MethodArgumentTypeMismatchException.message")
                + exception.getRequiredType().getName();
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(code, message, detailMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String code = "9";
        String message = getMessage("AuthenticationException.message");
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse errorResponse = new ErrorResponse(code, message, detailMessage);

        // convert object to json
        String json = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(errorResponse);

        // return json
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        String code = "10";
        String message = getMessage("AccessDeniedException.message");
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse errorResponse = new ErrorResponse(code, message, detailMessage);

        // convert object to json
        String json = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(errorResponse);

        // return json
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }
}