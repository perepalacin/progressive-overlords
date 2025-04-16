@param progressive_overlords.entities.dao.WorkoutExerciseDao workoutExercise
@param int workoutId
@param progressive_overlords.entities.dao.ExerciseDao exercise
!{String randomId = "exercise-" + String.valueOf(new java.util.Random().nextInt(900000) + 100000);}

<div class="flex-row align-center justify-between mb-1 px-1 exercise-header" id="${randomId}">
    @template.components.exercises.exercise-preview(exercise = exercise)
    <div class="flex-row gap-025">
        <button type="button" class="ghost-button-icon" hx-get="/api/v1/exercises/selector" hx-trigger="click" hx-target="#${randomId}" hx-swap="outerHTML">
            <img loading="lazy" src="../icons/repeat-2.svg" style="width: 1rem" />
        </button>
        <button type="button" class="ghost-button-icon ${workoutExercise != null ? "" : "remove-exercise-btn"}" hx-delete="${workoutExercise != null ? "/api/v1/sets/exercise?workoutId=" + workoutId + "&exerciseNum=" + workoutExercise.getExerciseNum() + "&exerciseId=" + workoutExercise.getExerciseId() : null}" hx-swap="outerHTML" hx-target="closest .exercise-item">
            <img loading="lazy" src="../icons/trash.svg" style="width: 1rem" />
        </button>
    </div>
    <input class="hidden" value="${exercise.getId()}" name="exerciseIds[]" required />
</div>
