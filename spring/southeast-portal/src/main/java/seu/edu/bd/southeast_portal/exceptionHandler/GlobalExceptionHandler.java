package seu.edu.bd.southeast_portal.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    ProblemDetail errorDetails;

    @ExceptionHandler(value = BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException e) {
        errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        return errorDetails;
    }

    @ExceptionHandler(value = IllegalAccessException.class)
    public ProblemDetail handleIllegalAccessException(IllegalAccessException e) {
        errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        errorDetails.setDetail("");
        return errorDetails;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {
        errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status","failed");
        errorDetails.setProperty("error","Required parameter is missing");
        return errorDetails;
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status","failed");
        errorDetails.setProperty("error","Required parameter is missing");
        return errorDetails;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status","failed");
        errorDetails.setProperty("error","Method not allowed");
        return errorDetails;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status", "failed");
        errorDetails.setProperty("error", "Invalid input type");
        return errorDetails;
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ProblemDetail handleHttpMessageNotWritable(HttpMessageNotWritableException ex) {
        ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status", "failed");
        errorDetails.setProperty("error", "The server encountered an issue while processing the response." +
                " One of the fields is missing or null.");
        return errorDetails;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handleHttpMessageNotWritable(NoResourceFoundException ex) {
        ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status", "failed");
        errorDetails.setProperty("error", "Requested url not found");
        return errorDetails;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotWritable(HttpMessageNotReadableException ex) {
        ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status", "failed");
        errorDetails.setProperty("error", "Required request body is missing");
        return errorDetails;
    }

    @ExceptionHandler(NumberFormatException.class)
    public ProblemDetail handleHttpMessageNotWritable(NumberFormatException ex) {
        ProblemDetail errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status", "failed");
        errorDetails.setProperty("error", "Value must be a number");
        return errorDetails;
    }

    @ExceptionHandler(value = Exception.class)
    public ProblemDetail handleException(Exception e) {
        e.printStackTrace();
        errorDetails = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        errorDetails.setDetail("");
        errorDetails.setProperty("status","failed");
        errorDetails.setProperty("error","Something went wrong");
        return errorDetails;
    }
}
