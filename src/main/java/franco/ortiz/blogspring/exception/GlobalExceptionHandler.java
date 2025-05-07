package franco.ortiz.blogspring.exception;

import franco.ortiz.blogspring.dto.common.ErrorDetailsDTO;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalHandling(Exception exception, WebRequest request){
        log.error(exception.getClass().getName());
        return new ResponseEntity<>(ErrorDetailsDTO.builder()
                .timestamp(LocalDateTime.now())
                .details(List.of(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()))
                .message(request.getDescription(Boolean.FALSE))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> resourceAccessHandling(ResourceAlreadyExistsException exception, WebRequest request){
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationHandling(ValidationException exception, WebRequest request){
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> jsonNotReadableHandling(
            HttpMessageNotReadableException exception, WebRequest request){
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequestValidationExceptions(
            MethodArgumentNotValidException exception, WebRequest request) {
        return new ResponseEntity<>(this.buildMultipleErrorDetailsDTO(exception, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> badRequestTypeMismatchExceptions(
            MethodArgumentTypeMismatchException exception, WebRequest request) {
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsHandling(BadCredentialsException exception, WebRequest request){
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestHandling(BadRequestException exception, WebRequest request){
        return new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.BAD_REQUEST);
    }

    private ErrorDetailsDTO buildSingleErrorDetailsDTO(Exception exception, WebRequest request){
        return ErrorDetailsDTO.builder()
                .timestamp(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .message(request.getDescription(Boolean.FALSE))
                .build();
    }

    private ErrorDetailsDTO buildMultipleErrorDetailsDTO(BindException exception, WebRequest request){
        List<String> details = exception.getBindingResult().getAllErrors()
                .stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return fieldName + ": " + errorMessage;
                })
                .toList();
        return ErrorDetailsDTO.builder()
                .timestamp(LocalDateTime.now())
                .details(details)
                .message(request.getDescription(Boolean.FALSE))
                .build();
    }


    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException exception, WebRequest request) {
        return new ResponseEntity<>(ErrorDetailsDTO.builder()
                .timestamp(LocalDateTime.now())
                .details(List.of("File upload error", exception.getMessage()))
                .message("Multipart request could not be processed.")
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        return new ResponseEntity<>(ErrorDetailsDTO.builder()
                .timestamp(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .message(request.getDescription(false))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> handleMissingServletRequestPartException(MissingServletRequestPartException exception,  WebRequest request) {
        return  new ResponseEntity<>(this.buildSingleErrorDetailsDTO(exception, request), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException exception, WebRequest request) {
        return new ResponseEntity<>(ErrorDetailsDTO.builder()
                .timestamp(LocalDateTime.now())
                .details(List.of(exception.getMessage()))
                .message(request.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }
}