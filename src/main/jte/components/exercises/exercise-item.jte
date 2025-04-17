@param progressive_overlords.entities.dao.ExerciseDao exercise
<li class="exercise-dropdown-item" hx-trigger="click" hx-get="/api/v1/exercises/data/${exercise.getId()}" hx-target="#exercise-data-container" hx-swap="innerHTML">
    <img loading="lazy" src="${exercise.getThumbnail()}" />
    <p>${exercise.getName()}</p>
</li>

