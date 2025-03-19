package progressive_overlords.entities.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExerciseDao {
    private int id;
    private String name;
    private String description;
    private String thumbnail;
}


