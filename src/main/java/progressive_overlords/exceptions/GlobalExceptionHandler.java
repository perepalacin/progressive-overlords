package progressive_overlords.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Void> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "error: " + ex.getMessage())
                .build();
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    public ResponseEntity<Void> handleAuthenticationExceptions(org.springframework.security.core.AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "error: Invalid username or password")
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Void> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorMessages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        String allMessages = String.join("; ", errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "error: " + allMessages)
                .build();
    }

    @ExceptionHandler(UsernameAlreadyExistsExcpetion.class)
    public ResponseEntity<Void> handleUsernameAlreadyTakenException(UsernameAlreadyExistsExcpetion ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .header("HX-Trigger", "ShowToast")
                .header("X-Message", "error: Username already taken")
                .build();
    }

}
