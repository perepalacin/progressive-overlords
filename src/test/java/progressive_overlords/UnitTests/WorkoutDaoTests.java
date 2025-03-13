package progressive_overlords.UnitTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import progressive_overlords.entities.dao.SetDao;
import progressive_overlords.entities.dao.WorkoutDao;
import progressive_overlords.entities.dto.SetDto;
import progressive_overlords.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutDaoTests {

    @Test
    public void parseSortedSetsFromRequest_ShouldSortSetsCorrectly() {

        WorkoutDao sut = new WorkoutDao();
        List<SetDto> sets = Arrays.asList(
                new SetDto(0,  12, 85, false),
                new SetDto(0,  10, 90, false),
                new SetDto(0,  8, 80, false),
                new SetDto(1,  12, 95, false),
                new SetDto(1,  10, 100, false)
        );

        sut.setExercisesDaoFromSetsDto(sets);

        List<SetDao> flatSets = sut.getFlatSetsList();

        List<Integer> actualExerciseNum = new ArrayList<>();
        List<Integer> actualSetNum = new ArrayList<>();
        List<Integer> actualExerciseId = new ArrayList<>();
        for (SetDao set : flatSets) {
            actualExerciseNum.add(set.getExerciseNum());
            actualSetNum.add(set.getSetNum());
            actualExerciseId.add(set.getExerciseId());
        }

        Assertions.assertEquals(Arrays.asList(0,0,0,1,1), actualExerciseNum, "The exercises nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(0,1,2,0,1), actualSetNum, "The set nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(0,0,0,1,1), actualExerciseId, "The set nums should be in the correct order.");
    }

    @Test
    public void parseSetsFromRequest_UnorderedInputCase1() {
        WorkoutDao sut = new WorkoutDao();
        List<SetDto> sets = Arrays.asList(
                new SetDto(1,  10, 100, false),
                new SetDto(0,  8, 80, false),
                new SetDto(0,  12, 85, false),
                new SetDto(1,  12, 95, false),
                new SetDto(0,  10, 90, false)
        );

        sut.setExercisesDaoFromSetsDto(sets);

        List<SetDao> flatSets = sut.getFlatSetsList();
        List<Integer> actualExerciseNum = new ArrayList<>();
        List<Integer> actualSetNum = new ArrayList<>();
        List<Integer> actualExerciseId = new ArrayList<>();
        for (SetDao set : flatSets) {
            actualExerciseNum.add(set.getExerciseNum());
            actualSetNum.add(set.getSetNum());
            actualExerciseId.add(set.getExerciseId());
        }

        Assertions.assertEquals(Arrays.asList(0,0,1,1,1), actualExerciseNum, "The exercises nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(0,1,0,1,2), actualSetNum, "The set nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(1,1,0,0,0), actualExerciseId, "The set nums should be in the correct order.");
    }

    @Test
    public void parseSetsFromRequest_UnorderedInputCase2() {
        WorkoutDao sut = new WorkoutDao();
        List<SetDto> sets = Arrays.asList(
                new SetDto(1,  10, 100, false),
                new SetDto(0,  8, 80, false),
                new SetDto(2,  10, 90, false),
                new SetDto(0,  10, 90, false),
                new SetDto(0,  12, 85, false),
                new SetDto(1,  12, 95, false),
                new SetDto(0,  10, 90, false),
                new SetDto(2,  10, 90, false)
        );

        sut.setExercisesDaoFromSetsDto(sets);

        List<SetDao> flatSets = sut.getFlatSetsList();
        List<Integer> actualExerciseNum = new ArrayList<>();
        List<Integer> actualSetNum = new ArrayList<>();
        List<Integer> actualExerciseId = new ArrayList<>();
        for (SetDao set : flatSets) {
            actualExerciseNum.add(set.getExerciseNum());
            actualSetNum.add(set.getSetNum());
            actualExerciseId.add(set.getExerciseId());
        }

        Assertions.assertEquals(Arrays.asList(0,0,1,1,1,1,2,2), actualExerciseNum, "The exercises nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(0,1,0,1,2,3,0,1), actualSetNum, "The set nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(1,1,0,0,0,0,2,2), actualExerciseId, "The set nums should be in the correct order.");
    }

    @Test
    public void parseSetsFromRequest_UnorderedInputCase3() {
        WorkoutDao sut = new WorkoutDao();
        List<SetDto> sets = Arrays.asList(
                new SetDto(3,  10, 90, false),
                new SetDto(0,  10, 90, false),
                new SetDto(3,  10, 90, false),
                new SetDto(0,  10, 100, false),
                new SetDto(1,  12, 95, false),
                new SetDto(3,  10, 90, false),
                new SetDto(3,  10, 90, false),
                new SetDto(0,  8, 80, false),
                new SetDto(1,  12, 85, false),
                new SetDto(0,  10, 90, false),
                new SetDto(2,  10, 90, false)
        );

        sut.setExercisesDaoFromSetsDto(sets);

        List<SetDao> flatSets = sut.getFlatSetsList();
        List<Integer> actualExerciseNum = new ArrayList<>();
        List<Integer> actualSetNum = new ArrayList<>();
        List<Integer> actualExerciseId = new ArrayList<>();
        for (SetDao set : flatSets) {
            actualExerciseNum.add(set.getExerciseNum());
            actualSetNum.add(set.getSetNum());
            actualExerciseId.add(set.getExerciseId());
        }

        Assertions.assertEquals(Arrays.asList(0,0,0,0,1,1,1,1,2,2,3), actualExerciseNum, "The exercises nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(0,1,2,3,0,1,2,3,0,1,0), actualSetNum, "The set nums should be in the correct order.");
        Assertions.assertEquals(Arrays.asList(3,3,3,3,0,0,0,0,1,1,2), actualExerciseId, "The set nums should be in the correct order.");
    }

    @Test
    public void parseSetsFromRequest_EmptyInput() {
        List<SetDto> sets = new ArrayList<>();
        WorkoutDao sut = new WorkoutDao();

        BadRequestException exception = Assert.assertThrows(BadRequestException.class, () ->
                sut.setExercisesDaoFromSetsDto(sets)
        );

        Assert.assertEquals("No sets were provided.", exception.getMessage());
    }

    @Test
    public void parseSetsFromRequest_NullInput() {
        WorkoutDao sut = new WorkoutDao();
        BadRequestException exception = Assert.assertThrows(BadRequestException.class, () ->
                sut.setExercisesDaoFromSetsDto(null)
        );

        Assert.assertEquals("No sets were provided.", exception.getMessage());
    }

}
