let exerciseCounter = 0;
let globalSetIndex = 0;

function addExercise() {
    exerciseCounter++;
    const exerciseList = document.getElementById("exerciseList");

    const exerciseHTML = `
        <div class="exercise-item border px-4 py-2 mb-2 rounded-md">
            <div class="flex flex-row align-center justify-between py-2">
                <label>Exercise:</label>
                <button
                    type="button"
                    onclick="removeExercise(this)"
                    class="text-red-500 font-bold"
                >
                    <img src="../icons/trash.svg" style="width: 1rem" />
                </button>
            </div>

            <input
                type="number"
                name="exerciseId-${exerciseCounter}"
                required
                class="border px-2 py-1 rounded-md w-full mb-2"
                placeholder="Exercise ID"
            />

            <div class="sets-container" data-exercise="${exerciseCounter}">
                <table class="w-full border-collapse border rounded-md">
                    <thead class="bg-gray-100">
                        <tr>
                            <th class="p-2">Set Num</th>
                            <th class="p-2">Reps</th>
                            <th class="p-2">Weight</th>
                            <th class="p-2">Warmup</th>
                            <th class="p-2"></th>
                        </tr>
                    </thead>
                    <tbody id="setTableBody-${exerciseCounter}">
                        <tr>
                            <td class="p-2 text-center">1</td>
                            <input
                                type="hidden"
                                name="sets[\${GLOBAL_INDEX}].setnum"
                                value="1"
                            />
                            <input
                                type="hidden"
                                name="sets[\${GLOBAL_INDEX}].exerciseId"
                                id="exerciseIdHidden-\${GLOBAL_INDEX}"
                            />
                            <td class="p-2">
                                <input
                                    type="number"
                                    name="sets[\${GLOBAL_INDEX}].reps"
                                    placeholder="12"
                                    required
                                    class="border px-2 py-1 rounded-md w-full text-center"
                                />
                            </td>
                            <td class="p-2">
                                <input
                                    type="number"
                                    name="sets[\${GLOBAL_INDEX}].weight"
                                    placeholder="44"
                                    required
                                    class="border px-2 py-1 rounded-md w-full text-center"
                                />
                            </td>
                            <td class="p-2 text-center">
                                <input
                                    type="checkbox"
                                    name="sets[\${GLOBAL_INDEX}].warmup"
                                    value="true"
                                />
                            </td>
                            <td class="p-2 text-center">
                                <button
                                    type="button"
                                    onclick="removeSet(this)"
                                    class="text-red-500 font-bold"
                                >
                                    <img src="../icons/trash.svg" style="width: 1rem" />
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <button
                type="button"
                onclick="addSet(${exerciseCounter})"
                class="bg-blue-500 px-4 py-2 rounded-md"
            >
                + Add Set
            </button>
        </div>
    `;

    const filledHTML = exerciseHTML.replace(/\${GLOBAL_INDEX}/g, globalSetIndex);
    exerciseList.insertAdjacentHTML("beforeend", filledHTML);

    const userExerciseIdInput = document.querySelector(
        `input[name="exerciseId-${exerciseCounter}"]`
    );
    const hiddenExerciseIdInput = document.getElementById(
        `exerciseIdHidden-${globalSetIndex}`
    );
    if (userExerciseIdInput && hiddenExerciseIdInput) {
        userExerciseIdInput.addEventListener("input", () => {
            hiddenExerciseIdInput.value = userExerciseIdInput.value;
        });
    }

    globalSetIndex++;
}

function addSet(exerciseNum) {
    const tableBody = document.getElementById(`setTableBody-${exerciseNum}`);
    const setCount = tableBody.children.length + 1;

    const rowHTML = `
        <tr>
            <td class="p-2 text-center">${setCount}</td>
            <input
                type="hidden"
                name="sets[${globalSetIndex}].setnum"
                value="${setCount}"
            />
            <input
                type="hidden"
                name="sets[${globalSetIndex}].exerciseId"
                id="exerciseIdHidden-${globalSetIndex}"
            />
            <td class="p-2">
                <input
                    type="number"
                    name="sets[${globalSetIndex}].reps"
                    placeholder="12"
                    required
                    class="border px-2 py-1 rounded-md w-full text-center"
                />
            </td>
            <td class="p-2">
                <input
                    type="number"
                    name="sets[${globalSetIndex}].weight"
                    placeholder="44"
                    required
                    class="border px-2 py-1 rounded-md w-full text-center"
                />
            </td>
            <td class="p-2 text-center">
                <input
                    type="checkbox"
                    name="sets[${globalSetIndex}].warmup"
                    value="true"
                />
            </td>
            <td class="p-2 text-center">
                <button
                    type="button"
                    onclick="removeSet(this)"
                    class="text-red-500 font-bold"
                >
                    <img src="../icons/trash.svg" style="width: 1rem" />
                </button>
            </td>
        </tr>
    `;

    tableBody.insertAdjacentHTML("beforeend", rowHTML);

    const userExerciseIdInput = document.querySelector(
        `input[name="exerciseId-${exerciseNum}"]`
    );
    const hiddenExerciseIdInput = document.getElementById(
        `exerciseIdHidden-${globalSetIndex}`
    );
    if (userExerciseIdInput && hiddenExerciseIdInput) {
        hiddenExerciseIdInput.value = userExerciseIdInput.value;
        userExerciseIdInput.addEventListener("input", () => {
            hiddenExerciseIdInput.value = userExerciseIdInput.value;
        });
    }

    globalSetIndex++;
}

function removeSet(button) {
    button.closest("tr").remove();
}

function removeExercise(button) {
    button.closest(".exercise-item").remove();
}