package progressive_overlords.entities.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    //TODO: ADD validation rules
    private String username;
    private String password;
    private String weightUnits;
}
