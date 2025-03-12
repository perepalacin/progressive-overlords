@param progressive_overlords.entities.dao.WorkoutDao template

@template.pages.layout.main(tabName = template != null ? "Edit template" : "Create a template",content = @`
<div  class="flex flex-col gap-2 w-full md:w-[600px]">
    <h1 class="text-xl font-semibold">${template != null ? "Edit template" : "Create a template"}</h1>
    <form
            id="workoutForm"
            hx-post="${template == null ? "/api/v1/workouts/templates" : null}"
            hx-patch="${template != null ? "/api/v1/workouts/templates" : null}"
            hx-trigger="submit"
            hx-target="this"
            hx-swap="outerHTML"
            class="flex flex-col gap-2 px-4 py-2 rounded-md border"
    >
        @if (template != null)
            <input style="display: none" type="number" name="id" required value="${template.getId()}">
        @endif

        <label class="block font-semibold">Template Name:</label>
        <input type="text" name="name" required class="w-full px-3 py-2 border rounded-md mb-2" placeholder="Enter template name" value="${template != null ? template.getName() : ""}">

        <label class="block font-semibold">Description:</label>
        <textarea maxlength="511" rows="3" type="text" name="description" class="w-full px-3 py-2 border rounded-md mb-2" placeholder="Enter description">${template != null ? template.getDescription() : ""}</textarea>

        <label class="block font-semibold">Color:</label>
        <div class="relative flex items-center gap-2">
            <input type="color" name="color" class="h-10 w-10 px-1 py-1 border rounded-md cursor-pointer" id="colorPicker" value="${template != null ? template.getColor() : "#00FF00"}">
            <span id="colorHex" class="font-mono text-sm"></span>
        </div>

        <label class="block font-semibold">Body Part:</label>
        <input type="text" name="bodyPart" class="w-full px-3 py-2 border rounded-md mb-2" placeholder="e.g. arms, legs" value="${template != null ? template.getBodyPart() : ""}">

        <label class="block font-semibold">Tags (comma-separated):</label>
        <input type="text" name="unparsedTags" class="w-full px-3 py-2 border rounded-md mb-2" placeholder="e.g. strength, endurance" value="${template != null ? template.getUnparsedTags() : ""}">

        <div id="exerciseContainer">
            <h3 class="font-bold mt-4">Exercises</h3>
            @if (template.getSets() != null)
                @for (progressive_overlords.entities.dao.SetDao sets : template.getSets())
                    <div class="exercise-item flex gap-2 mb-2">
                        <input type="number" name="exercisesId[]" placeholder="Exercise ID" required class="px-3 py-2 border rounded-md w-1/3">
                        <input type="number" name="sets[]" placeholder="Sets" required class="px-3 py-2 border rounded-md w-1/3">
                        <input type="number" name="reps[]" placeholder="Reps" required class="px-3 py-2 border rounded-md w-1/3">
                        <button type="button" onclick="removeExercise(this)" class="text-red-500 font-bold">&times;</button>
                    </div>
                @endfor
            @endif
                <div id="exerciseList"></div>
        </div>

        <button type="button" onclick="addExercise()">+ Add Exercise</button>

        <button type="submit" class="bg-green-500 text-white px-3 py-2 rounded-md mt-4 w-full">
            ${template == null ? "Create Template" : "Edit Template"}
        </button>
    </form>
</div>
<script src="../scripts/add-exercise.js"></script>
<script src="https://unpkg.com/htmx-ext-json-enc@2.0.1/json-enc.js"></script>


`)