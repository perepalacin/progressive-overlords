@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises
@param int page
@param String query

@if (page == 0)
    <div class="exercises-drop-down-response">
        @template.responses.exercises.exercises-dropdown-list(exercises = exercises, page = page, query = query)
    </div>
@else
    @template.responses.exercises.exercises-dropdown-list(exercises = exercises, page = page, query = query)
@endif
