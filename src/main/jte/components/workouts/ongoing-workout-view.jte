<%--TODO: changing is warmup on patch doesn't work we need to create the send-set.js file and change the event listener--%>
@param progressive_overlords.entities.dao.WorkoutDao workout
<section class="w-full mb-6">
    <div id="workoutForm">
        <div class="flex-row align-center justify-between">
            <h1>${workout.getName()}</h1>
            @if(workout.getEndDate() == null)
            <div class="flex-row gap-1 align-center">
                <p id="timer">00:00:00</p>
                <button hx-patch="/api/v1/workouts/finish/${workout.getId()}" hx-swap="none" hx-trigger="click" class="button main-button" type="submit">
                    Finish
                </button>
            </div>
            @endif
        </div>
        <ul id="exerciseContainer">
            @if(workout != null && !workout.getExercises().isEmpty())

                @for(progressive_overlords.entities.dao.WorkoutExerciseDao exercise : workout.getExercises())
                    <li class="exercise-item">
                        @template.responses.exercises.exercise-header(workoutId = workout.getId(), exercise = exercise.getExercise(), workoutExercise = exercise)
                        <section class="template-input-grid">
                            <ul>
                                <li>Set</li>
                                <li>Warmup</li>
                                <li>Reps</li>
                                <li>Weight</li>
                                <li>
                                    <button type="button"  class="button muted-button mobile-hidden add-set-btn">
                                        + Add Set
                                    </button>
                                </li>
                            </ul>
                            @for(progressive_overlords.entities.dao.SetDao set : exercise.getSets())
                                @template.components.sets.workout-set(set = set)
                            @endfor
                        </section>
                        <div class="desktop-hidden w-full mb-1" style="padding: 0rem 1rem;">
                            <button type="button" class="button muted-button w-full add-set-btn">
                                + Add Set
                            </button>
                        </div>
                    </li>
                @endfor
            @else
                <li class="exercise-item">
                    @template.responses.exercises.exercises-selector()
                    <section class="template-input-grid">
                        <ul>
                            <li>Set</li>
                            <li>Warmup</li>
                            <li>Reps</li>
                            <li>Weight</li>
                            <li>
                                <button type="button"  class="button muted-button mobile-hidden add-set-btn">
                                    + Add Set
                                </button>
                            </li>
                        </ul>
                        <ul class="setTableRow">
                            <li class="font-bold set-num">1</li>
                            <li>
                                <input class="border-none" name="warmup" checked="checked" type="checkbox">
                            </li>
                            <li>
                                <input type="number" name="reps" placeholder="12" required>
                            </li>
                            <li>
                                <input type="number" name="weight" placeholder="44" required >
                            </li>
                            <li>
                                <button type="button"  class="ghost-button-icon remove-set-btn" >
                                    <img src="../icons/x.svg" style="width: 1rem" />
                                </button>
                            </li>
                        </ul>
                    </section>
                    <div class="desktop-hidden w-full mb-1" style="padding: 0rem 1rem;">
                        <button type="button" class="button muted-button w-full add-set-btn">
                            + Add Set
                        </button>
                    </div>
                </li>
            @endif
        </ul>
        <div class="flex-row justify-end">
            <button class="button main-button" type="button" id="addExerciseBtn">+ Add Exercise</button>
        </div>
    </div>
</section>

<template id="setRowTemplate">
    <form hx-post="/api/v1/sets" hx-target="this" hx-swap="outerHTML">
        <ul class="setTableRow">
            <li class="font-bold set-num">
                1
                <input class="hidden" required value="0" name="workoutId" />
                <input class="hidden" required value="0" name="exerciseId" />
                <input class="hidden" required value="0" name="setNum" />
                <input class="hidden" required value="0" name="exerciseNum" />
            </li>
            <li>
                <input class="border-none" name="warmup" type="checkbox" >
            </li>
            <li>
                <input type="number" name="reps" placeholder="12" required>
            </li>
            <li>
                <input type="number" name="weight" placeholder="44" required >
            </li>
            <li>
                <button type="button"  class="ghost-button-icon remove-set-btn" >
                    <img src="../icons/x.svg" style="width: 1rem" />
                </button>
                <button type="submit"  class="ghost-button-icon" >
                    <img src="../icons/send-horizontal.svg" style="width: 1rem" />
                </button>
            </li>
        </ul>
    </form>
</template>

<template id="exerciseTemplate">
    <li class="exercise-item">
        @template.responses.exercises.exercises-selector()
    </li>
</template>

<template id="setTableTemplate">
    <section class="template-input-grid">
        <ul>
            <li>Set</li>
            <li>Warmup</li>
            <li>Reps</li>
            <li>Weight</li>
            <li>
                <button type="button" class="button muted-button mobile-hidden add-set-btn">
                    + Add Set
                </button>
            </li>
        </ul>
        <form hx-post="/api/v1/sets" hx-target="this" hx-swap="outerHTML">
            <ul class="setTableRow">
                <li class="font-bold set-num">
                    ${1}
                    <input class="hidden" required value="0" name="workoutId" />
                    <input class="hidden" required value="0" name="exerciseId" />
                    <input class="hidden" required value="1" name="setNum" />
                    <input class="hidden" required value="0" name="exerciseNum" />
                </li>
                <li>
                    <input class="border-none" name="warmup" checked="false" type="checkbox">
                </li>
                <li>
                    <input type="number" name="reps" placeholder="12" required>
                </li>
                <li>
                    <input type="number" name="weight" placeholder="25" required>
                </li>
                <li class="flex-row gap-05">
                    <button type="button"  class="ghost-button-icon remove-set-btn" >
                        <img src="../icons/x.svg" style="width: 1rem" />
                    </button>
                    <button type="submit"  class="ghost-button-icon" >
                        <img src="../icons/send-horizontal.svg" style="width: 1rem" />
                    </button>
                </li>
            </ul>
        </form>
    </section>
    <div class="desktop-hidden w-full mb-1" style="padding: 0.5rem 1rem;">
        <button type="button" class="button muted-button w-full add-set-btn">
            + Add Set
        </button>
    </div>
</template>

@if(workout.getEndDate() == null)
<script src="../scripts/timer.js">
</script>
<script>
    startElapsedTimer("${workout.getStartDate()}");
</script>
@endif
<script type="module" src="../scripts/create-workout.js"></script>
<script type="module" src="../scripts/send-template.js"></script>