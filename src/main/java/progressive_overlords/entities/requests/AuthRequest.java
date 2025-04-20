package progressive_overlords.entities.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequest {
    @Size(min = 4, message = "Username must be at least 4 characters long")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()_+=\\-`~[{}\\]\\\\|:;\"'<>,.?/]).*$",
            message = "Password must contain at least one uppercase letter, one number, and one special character")
    private String password;

    private String weightUnits;
}
