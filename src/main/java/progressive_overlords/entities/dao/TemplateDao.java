package progressive_overlords.entities.dao;

import lombok.Getter;
import lombok.Setter;
import progressive_overlords.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TemplateDao {
    private int id;
    private String name;
    private String description;
    private String color;
    private String bodyPart;
    private List<String> tags;
    private String unparsedTags;
    private List<SetDao> sets;
    private String createdAt;
    private String updatedAt;

    public TemplateDao() {

    }

    public TemplateDao(int id, String name, String description, String color, String bodyPart, String tags) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.bodyPart = bodyPart;
        this.unparsedTags = tags;
        parseTags(tags);
    }

    public TemplateDao(int id, String name, String description, String color, String bodyPart, String tags, List<Integer> exercisesId, List<Integer> sets, List<Integer> reps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.bodyPart = bodyPart;
        this.unparsedTags = tags;
        parseTags(tags);
        parseSetsList(exercisesId, sets, reps);
    }

    public TemplateDao(String name, String description, String color, String bodyPart, String tags, List<Integer> exercisesId, List<Integer> sets, List<Integer> reps) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.bodyPart = bodyPart;
        this.unparsedTags = tags;
        parseTags(tags);
        parseSetsList(exercisesId, sets, reps);
    }

    public void parseSetsList(List<Integer> exercisesId, List<Integer> sets, List<Integer> reps)  {
        List<SetDao> setList = new ArrayList<>();

        try {
            for (int i = 0; i < sets.size(); i++) {
                SetDao newSet = SetDao.builder().id(i).exerciseId(exercisesId.get(i)).reps(reps.get(i)).build();
                setList.add(newSet);
            }
        } catch (Exception e) {
            throw new BadRequestException("There is a mismatch between the number of exercises and sets selected. Please review your template.");
        }

        this.sets = setList;
    }

    private void parseTags(String tags) {
        if (tags == null || tags.trim().isEmpty()) {
            this.tags = Collections.emptyList();
        }

        this.tags = Arrays.stream(tags.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
