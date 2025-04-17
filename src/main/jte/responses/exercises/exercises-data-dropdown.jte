@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises
@param int page
@param String query

@if (page == 0 && (exercises == null || exercises.isEmpty()))
    <p>No results found...</p>
@else
    @for(progressive_overlords.entities.dao.ExerciseDao exercise : exercises)
        @template.components.exercises.exercise-item(exercise = exercise)
    @endfor
@endif
@if (exercises != null && exercises.size() == 20)
    <button hx-get="/api/v1/exercises?data=true&page=${page+1}&query=${query}" hx-target="this" hx-trigger="click" hx-swap="outerHTML" type="button" class="button ghost-button w-full">
        Load more
    </button>
@endif
