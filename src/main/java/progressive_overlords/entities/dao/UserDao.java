package progressive_overlords.entities.dao;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDao {
    private UUID id;
    private String username;
    private String password;
    private String createdAt;
    private String updatedAt;
    private String weightUnits;
}
