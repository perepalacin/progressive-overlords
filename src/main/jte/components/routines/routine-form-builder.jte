@param progressive_overlords.entities.dao.WorkoutDao routine
<section class="w-full mb-6">
    <form id="workoutForm" hx-trigger="submit" hx-post="${routine == null ? "/api/v1/routines" : null}" hx-patch="${routine != null ? "/api/v1/routines" : null}" hx-swap="none">
        <div class="flex-row align-center justify-between">
            <h1>Create a template</h1>
            <button class="button main-button" type="submit">
                Submit
            </button>
        </div>
        <label>Template Name:</label>
        <input type="text" name="name" required placeholder="Enter template name" class="mb-1" value="${routine != null ? routine.getName() : ""}">
        <label class="block font-semibold">Description:</label>
        <textarea maxlength="511" rows="3" type="text" class="mb-1" name="description"placeholder="Enter description">
            ${routine != null ? routine.getDescription() : ""}
        </textarea>

        <input class="hidden" type="checkbox" checked="true" name="isTemplate" />
        <ul id="exerciseContainer">
            <h3 class="m-0">Exercises</h3>
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
                            <input class="border-none" name="warmups[]" checked="checked" type="checkbox" hx-vals='{"purchased": "false"}'>
                        </li>
                        <li>
                            <input type="number" name="reps[]" placeholder="12" required>
                        </li>
                        <li>
                            <input type="number" name="weights[]" placeholder="44" required >
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
        </ul>
        <div class="flex-row justify-end">
            <button class="button main-button" type="button" id="addExerciseBtn">+ Add Exercise</button>
        </div>
    </form>
</section>

<template id="setRowTemplate">
    <ul class="setTableRow">
        <li class="font-bold set-num">1</li>
        <li>
            <input class="border-none" name="warmups[]" type="checkbox" >
        </li>
        <li>
            <input type="number" name="reps[]" placeholder="12" required>
        </li>
        <li>
            <input type="number" name="weights[]" placeholder="44" required >
        </li>
        <li>
            <button type="button"  class="ghost-button-icon remove-set-btn" >
                <img src="../icons/x.svg" style="width: 1rem" />
            </button>
        </li>
    </ul>
</template>

<template id="exerciseTemplate">
    <li class="exercise-item">
        @template.responses.exercises.exercises-selector()
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
            <ul class="setTableRow">
                <li class="font-bold set-num">1</li>
                <li>
                    <input class="border-none" name="warmups[]" type="checkbox">
                </li>
                <li>
                    <input type="number" name="reps[]" placeholder="12" required>
                </li>
                <li>
                    <input type="number" name="weights[]" placeholder="44" required >
                </li>
                <li>
                    <button type="button" class="ghost-button-icon remove-set-btn" >
                        <img src="../icons/x.svg" style="width: 1rem" />
                    </button>
                </li>
            </ul>
        </section>
        <div class="desktop-hidden w-full mb-1" style="padding: 0.5rem 1rem;">
            <button type="button" class="button muted-button w-full add-set-btn">
                + Add Set
            </button>
        </div>
    </li>
</template>
<script type="module" src="../scripts/create-template.js"></script>
<script type="module" src="../scripts/send-template.js"></script>