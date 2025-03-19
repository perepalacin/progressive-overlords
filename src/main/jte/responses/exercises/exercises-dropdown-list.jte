@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises

@for(progressive_overlords.entities.dao.ExerciseDao exercise : exercises)
    <li class="flex flex-row gap-2">
        <img src="${exercise.getThumbnail()}" />
        <p>${exercise.getName()}</p>
    </li>
@endfor