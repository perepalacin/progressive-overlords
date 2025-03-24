@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises
@param int page
@param String query

@if(exercises == null || exercises.isEmpty())
    @if(page == 0 )
        <p>No results found...</p>
    @endif
@else
    @for(progressive_overlords.entities.dao.ExerciseDao exercise : exercises)
        <li class="dropdown-item flex flex-row items-center justify-start gap-4 px-4 py-2 cursor-pointer hover:bg-gray-200">
            <img class="w-6 h-6 rounded-full" src="${exercise.getThumbnail()}" />
            <p>${exercise.getName()}</p>
        </li>
    @endfor
    @if(exercises.size() == 20)
        <button hx-trigger="click" hx-get="/api/v1/exercises?page=${page+1}&query=${query}" hx-target="this" hx-swap="outerHTML">
            Load more
        </button>
    @endif
@endif
