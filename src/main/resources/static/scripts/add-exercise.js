let exerciseCounter = 0;

function addExercise() {
    exerciseCounter++;
    const exerciseList = document.getElementById("exerciseList");

    const exerciseHTML = `
        <div class="exercise-item border px-4 py-2 mb-2 rounded-md">
            <div class="flex flex-row align-center justify-between py-2">
                <label>Exercise:</label>
                <button type="button" onclick="removeExercise(this)" class="text-red-500 font-bold">
                    <img src="../icons/trash.svg" style="width: 1rem" />
                </button>
            </div>
            <input type="number" name="exerciseId-${exerciseCounter}" required class="border px-2 py-1 rounded-md w-full mb-2">

            <div class="sets-container" data-exercise="${exerciseCounter}">
                <table class="w-full border-collapse border rounded-md">
                    <thead class="bg-gray-100">
                        <tr>
                            <th class="p-2">Set</th>
                            <th class="p-2">Reps</th>
                            <th class="p-2">Weight</th>
                            <th class="p-2">Warmup</th>
                            <th class="p-2"></th>
                        </tr>
                    </thead>
                    <tbody id="setTableBody-${exerciseCounter}">
                        <!-- Default Row -->
                        <tr>
                            <td class="p-2 text-center">1</td>
                            <td class="p-2">
                                <input type="number" name="reps-${exerciseCounter}[]" placeholder="12" required class="border px-2 py-1 rounded-md w-full text-center">
                            </td>
                            <td class="p-2">
                                <input type="number" name="weight-${exerciseCounter}[]" placeholder="44" required class="border px-2 py-1 rounded-md w-full text-center">
                            </td>
                            <td class="p-2 text-center">
                                <input type="checkbox" name="warmup-${exerciseCounter}[]" value="true">
                            </td>
                            <td class="p-2 text-center">
                                <button type="button" onclick="removeSet(this)" class="text-red-500 font-bold">
                                    <img src="../icons/trash.svg" style="width: 1rem" />
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <button type="button" onclick="addSet(${exerciseCounter})" class="bg-blue-500 px-4 py-2 rounded-md">
                + Add Set
            </button>
        </div>
    `;

    exerciseList.insertAdjacentHTML("beforeend", exerciseHTML);
}

function addSet(exerciseNum) {
    const tableBody = document.getElementById(`setTableBody-${exerciseNum}`);
    const setCount = tableBody.children.length + 1;

    const rowHTML = `
        <tr>
            <td class="p-2 text-center">${setCount}</td>
            <td class="p-2">
                <input type="number" name="reps-${exerciseNum}[]" placeholder="12" required class="border px-2 py-1 rounded-md w-full text-center">
            </td>
            <td class="p-2">
                <input type="number" name="weight-${exerciseNum}[]" placeholder="44" required class="border px-2 py-1 rounded-md w-full text-center">
            </td>
            <td class="p-2 text-center">
                <input type="checkbox" name="warmup-${exerciseNum}[]" value="true">
            </td>
            <td class="p-2 text-center">
                <button type="button" onclick="removeSet(this)" class="text-red-500 font-bold">
                    <img src="../icons/trash.svg" style="width: 1rem" />
                </button>
            </td>
        </tr>
    `;

    tableBody.insertAdjacentHTML("beforeend", rowHTML);
}

function removeSet(button) {
    button.closest("tr").remove();
}

function removeExercise(button) {
    button.closest(".exercise-item").remove();
}

// Prepare the data when submitting via HTMX
function prepareDataForHTMX(formElement) {
    const workoutData = {
        name: formElement.querySelector('input[name="name"]').value,
        description: formElement.querySelector('textarea[name="description"]').value,
        color: formElement.querySelector('input[name="color"]').value,
        bodyPart: formElement.querySelector('input[name="bodyPart"]').value,
        unparsedTags: formElement.querySelector('input[name="unparsedTags"]').value,
        // Use setsDto instead of sets to match the WorkoutDto field name
        setsDto: []
    };

    // Loop through all exercises and collect their set data
    formElement.querySelectorAll('.exercise-item').forEach((exerciseElement, index) => {
        const exerciseId = exerciseElement.querySelector(`input[name="exerciseId-${index + 1}"]`).value;
        const sets = [];

        // Gather all sets for this exercise
        const setRows = exerciseElement.querySelectorAll(`#setTableBody-${index + 1} tr`);
        setRows.forEach((row, setIndex) => {
            sets.push({
                // Rename setnum -> setNum to match SetDto
                setNum: setIndex + 1,
                exerciseId: exerciseId,
                reps: row.querySelector(`input[name="reps-${index + 1}[]"]`).value,
                weight: row.querySelector(`input[name="weight-${index + 1}[]"]`).value,
                warmup: row.querySelector(`input[name="warmup-${index + 1}[]"]`).checked
            });
        });

        // Push those sets into the setsDto array
        workoutData.setsDto.push(...sets);
    });

    return workoutData;
}

document.getElementById('workoutForm').addEventListener('htmx:beforeRequest', function (event) {
    const form = event.target;
    const workoutData = prepareDataForHTMX(form);

    if (!event.detail.headers) {
        event.detail.headers = {};
    }

    event.detail.headers['Content-Type'] = 'application/json';  // Set content-type to application/json

    event.detail.parameters = JSON.stringify(workoutData);
});