package progressive_overlords.entities.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkoutDao extends TemplateDao {

    private String startDate;
    private String endDate;


    public WorkoutDao(int id, String name, String description, String color, String bodyPart, String tags, String startDate, String endDate, List<Integer> exercisesId, List<Integer> sets, List<Integer> reps) {
        super(id, name, description, color, bodyPart, tags, exercisesId, sets, reps);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WorkoutDao(TemplateDao templateDao, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
