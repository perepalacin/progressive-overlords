package progressive_overlords.entities.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplateDao {
    private String name;
    private String description;
    private String color;
    private String bodyPart;
    private List<String> tags;
    private List<SetDao> sets;
    private String createdAt;
    private String updatedAt;
}
