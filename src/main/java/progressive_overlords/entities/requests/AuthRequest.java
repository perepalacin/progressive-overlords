package progressive_overlords.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequest {
    //TODO: ADD validation rules
    private String username;
    private String password;
    private String weightUnits;
}
