@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises
@param int page
@param String query

@if(exercises == null || exercises.isEmpty())
    @if(page == 0 )
        <li class="exercise-dropdown-item">
            <p>No results found...</p>
        </li>
    @endif
@else
    @for(progressive_overlords.entities.dao.ExerciseDao exercise : exercises)
        <li class="exercise-dropdown-item" hx-trigger="click" hx-get="/api/v1/exercises/preview/${exercise.getId()}" hx-target="closest .exercise-header" hx-swap="outerHTML">
            <img loading="lazy" src="${exercise.getThumbnail()}" />
            <p>${exercise.getName()}</p>
        </li>
    @endfor
    @if(exercises.size() == 20)
        <div class="w-full">
            <button hx-trigger="click" hx-get="/api/v1/exercises?page=${page+1}&query=${query}" hx-target="closest div" hx-swap="outerHTML" type="button" class="button ghost-button w-full">
                Load more
            </button>
        </div>
    @endif
@endif