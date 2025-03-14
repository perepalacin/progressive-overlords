@param progressive_overlords.entities.dao.WorkoutDao template

@template.pages.layout.main(tabName = template != null ? "Edit template" : "Create a template",content = @`
<div  class="flex flex-col gap-2 w-full md:w-[600px]">
    <h1 class="text-xl font-semibold">${template != null ? "Edit template" : "Create a template"}</h1>
    <form id="workoutForm" hx-post="${template == null ? "/api/v1/workouts/templates" : null}" hx-patch="${template != null ? "/api/v1/workouts/templates" : null}" hx-trigger="submit" hx-target="this" hx-swap="outerHTML" class="flex flex-col gap-2 px-4 py-2 rounded-md border">
        @if (template != null)
            <input style="display: none" type="number" name="templateId" required value="${template.getId()}">
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

        <label class="block font-semibold">Tags (comma-separated):</label>
        <input type="text" name="unparsedTags" class="w-full px-3 py-2 border rounded-md mb-2" placeholder="e.g. strength, endurance" value="${template != null ? template.getUnparsedTags() : ""}">

        <div id="exerciseContainer">
            <h3 class="font-bold mt-4">Exercises</h3>
            @if (template != null && template.getExercises() != null)
                @for(int i = 0; i < template.getExercises().size(); i++)
                        <div class="exercise-item border px-4 py-2 mb-2 rounded-md">
                            <div class="flex flex-row align-center justify-between py-2">
                                <label>Exercise:</label>
                                <button type="button" onclick="removeExercise(this)" class="text-red-500 font-bold">
                                    <img src="../icons/trash.svg" style="width: 1rem" />
                                </button>
                            </div>
                            <input type="number" name="exerciseId-${i}" required class="border px-2 py-1 rounded-md w-full mb-2" value="${template.getExercises().get(i).getExerciseId()}">

                            <div class="sets-container border-collapse border rounded-md" data-exercise="${i}">
                                <table class="w-full ">
                                    <thead class="bg-gray-100">
                                    <tr>
                                        <th class="p-2">Sets</th>
                                        <th class="p-2">Reps</th>
                                        <th class="p-2">Weight</th>
                                        <th class="p-2">Warmup</th>
                                        <th class="p-2"></th>
                                    </tr>
                                    </thead>
                                    <tbody id="setTableBody-${i}">
                                    @for (progressive_overlords.entities.dao.SetDao set : template.getExercises().get(i).getSets())
                                    <tr>
                                        <td class="p-2 text-center">${set.getSetNum() +1}</td>
                                        <td class="p-2">
                                            <input type="number" name="reps-${i}[]" placeholder="12" required class="border px-2 py-1 rounded-md w-full text-center" value="${set.getReps()}">
                                        </td>
                                        <td class="p-2">
                                            <input type="number" name="weight-${i}[]" placeholder="44" required class="border px-2 py-1 rounded-md w-full text-center" value="${set.getWeight()}">
                                        </td>
                                        <td class="p-2 text-center">
                                            <input type="checkbox" name="warmup-${i}[]" checked="${set.isWarmup()}">
                                        </td>
                                        <td class="p-2 text-center">
                                            <button type="button" onclick="removeSet(this)" class="text-red-500 font-bold">
                                                <img src="../icons/trash.svg" style="width: 1rem" />
                                            </button>
                                        </td>
                                    </tr>
                                    @endfor
                                    </tbody>
                                </table>
                            </div>
                            <button type="button" onclick="addSet(${i})" class="bg-blue-500 px-4 py-2 rounded-md">
                                + Add Set
                            </button>
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