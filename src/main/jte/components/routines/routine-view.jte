@param progressive_overlords.entities.dao.WorkoutDao routine
<section class="w-full mb-6">
    <div id="workoutForm">
        <div class="flex-row align-center justify-between">
            <h1>${routine.getName()}</h1>
            <div class="flex-row align-center gap-1">
                <a href="/create-routine/${routine.getId()}">
                    <button class="button main-button gap-05">
                        <img src="../icons/pen.svg" style="width: 1rem" />
                        Edit
                    </button>
                </a>
                <button class="button muted-button gap-05" hx-trigger="click" hx-delete="/api/v1/routines/123${routine.getId()}" hx-target="this" hx-swap="none">
                    <img src="../icons/trash.svg" style="width: 1rem" />
                    Delete
                </button>
            </div>
        </div>
        <label class="block font-semibold">Description:</label>
        <p>${routine.getDescription()}</p>
        <ul id="exerciseContainer">
            <h3 class="m-0">Exercises</h3>
            @if(routine != null && !routine.getExercises().isEmpty())
                @for(progressive_overlords.entities.dao.WorkoutExerciseDao exercise : routine.getExercises())
                    <li class="exercise-item">
                        @template.components.exercises.exercise-preview(exercise = exercise.getExercise())
                        <section class="template-input-grid template-view-grid">
                            <ul>
                                <li>Set</li>
                                <li>Warmup</li>
                                <li>Reps</li>
                                <li>Weight</li>
                            </ul>
                            <ul class="setTableRow">
                                @for(progressive_overlords.entities.dao.SetDao set : exercise.getSets())
                                    <li class="font-bold set-num">${set.getSetNum() +1}</li>
                                    <li>
                                        <input class="border-none" name="warmups[]" checked="${set.isWarmup()}" type="checkbox">
                                    </li>
                                    <li>
                                        <input type="number" name="reps[]" disabled="true" placeholder="12" required value="${set.getReps()}">
                                    </li>
                                    <li>
                                        <input type="number" name="weights[]" disabled="true" placeholder="44" required value="${set.getWeight()}" >
                                    </li>
                                @endfor
                            </ul>
                        </section>
                    </li>
                @endfor
            @endif
        </ul>
    </div>
</section>

