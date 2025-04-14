package progressive_overlords.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PublicUserDao {
    private UUID userId;
    private String username;
    private int workouts;
    private int followers;
    private int following;
}
