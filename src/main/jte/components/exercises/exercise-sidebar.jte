@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises

<%--DESKTOP--%>
<input type="search"
    name="query"
    required
    autocomplete="off"
    hx-trigger="input changed delay:500ms, keyup[key=='Enter'], click"
    hx-get="/api/v1/exercises?page=0&data=true"
    placeholder="Search exercises"
    hx-target="#exercises-sidebar"
    hx-swap="innerHTML"
    class="w-full"
>
<div id="exercises-sidebar">
    @for(progressive_overlords.entities.dao.ExerciseDao exercise : exercises)
        @template.components.exercises.exercise-item(exercise = exercise)
    @endfor
    <button hx-get="/api/v1/exercises?data=true&page=1" hx-target="this" hx-trigger="click" hx-swap="outerHTML" type="button" class="button ghost-button w-full">
        Load more
    </button>
</div>

<%--MOBILE--%>