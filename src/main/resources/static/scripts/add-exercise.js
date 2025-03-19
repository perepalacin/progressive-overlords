import { showToast } from './toasts.js';

let exerciseCounter = 0;

const page = window.location.pathname.split('/')[1] || '';
let workoutId = 0;
if (page === "workout") {
    workoutId = Number(window.location.pathname.split('/')[2]);
}
function addExercise() {
    const exerciseList = document.getElementById("exerciseContainer");
    if (exerciseList.querySelectorAll('.exercise-item').length > 0) {
        exerciseCounter = exerciseList.querySelectorAll('.exercise-item').length
    }
    console.log(exerciseCounter);
    let exerciseHTML = "";
    if (page === "create-template" || page === "edit-template") {
        exerciseHTML = `
    <div class="exercise-item border px-4 py-2 mb-2 rounded-md">
        <div class="flex flex-row align-center justify-between py-2">
            <label>Exercise:</label>
            <button type="button" data-exercise="${exerciseCounter}" class="remove-exercise-btn text-red-500 font-bold">
                <img src="../icons/trash.svg" style="width: 1rem" />
            </button>
        </div>
        <input type="number" name="exerciseId-${exerciseCounter}" required class="border px-2 py-1 rounded-md w-full mb-2">

        <div class="sets-container" data-exercise="${exerciseCounter}">
            <section class="w-full border-collapse border rounded-md">
                <ul class="flex flex-row items-center bg-gray-100">
                        <li class="w-[15%] text-center p-2">Set</li>
                        <li class="w-[20%] text-center p-2">Warmup</li>
                        <li class="w-[25%] text-center p-2">Reps</li>
                        <li class="w-[25%] text-center p-2">Weight</li>
                        <li class="w-[15%] text-center p-2"></li>
                </ul>
                <ul id="setTableBody-${exerciseCounter}">
                    <li class="flex flex-row items-center">
                        <div class="w-[15%] text-center p-2 text-center">1</div>
                        <div class="w-[20%] text-center p-2 text-center">
                                                    <input type="checkbox" name="warmup-${exerciseCounter}[]" value="true">
                                                </div>
                        <div class="w-[25%] text-center p-2">
                            <input type="number" name="reps-${exerciseCounter}[]" placeholder="12" required
                                class="border px-2 py-1 rounded-md w-full text-center">
                        </div>
                        <div class="w-[25%] text-center p-2">
                            <input type="number" name="weight-${exerciseCounter}[]" placeholder="20" required
                                class="border px-2 py-1 rounded-md w-full text-center">
                        </div>
                        <div class="w-[15%] text-center p-2 text-center">
                            <button type="button" data-exercise="${exerciseCounter}"
                                class="remove-set-btn text-red-500 font-bold">
                                <img src="../icons/trash.svg" style="width: 1rem" />
                            </button>
                        </div>
                    </li>
                </ul>
            </section>
        </div>
        <button type="button" data-exercise="${exerciseCounter}" class="add-set-btn bg-blue-500 px-4 py-2 rounded-md">
            + Add Set
        </button>
    </div>
`;
    } else if (page === "workout") {
        exerciseHTML = `
<div class="exercise-item border px-4 py-2 mb-2 rounded-md">
    <div class="flex flex-row align-center justify-between py-2">
        <label>Exercise:</label>
        <button type="button" data-exercise="${exerciseCounter}" class="remove-exercise-btn text-red-500 font-bold">
            <img src="../icons/trash.svg" style="width: 1rem" />
        </button>
    </div>
    <input type="number" name="exerciseId-${exerciseCounter}" required class="border px-2 py-1 rounded-md w-full mb-2">

    <div class="sets-container" data-exercise="${exerciseCounter}">
        <section class="w-full border-collapse border rounded-md">
            <ul class="flex flex-row items-center bg-gray-100">
                <li class="w-[10%] text-center p-2">Set</li>
                <li class="w-[20%] text-center p-2">Warmup</li>
                <li class="w-[25%] text-center p-2">Reps</li>
                <li class="w-[25%] text-center p-2">Weight</li>
                <li class="w-[10%] text-center p-2"></li>
                <li class="w-[10%] text-center p-2"></li>
            </ul>
            <ul id="setTableBody-${exerciseCounter}">
                <li class="flex flex-row items-center">
                    <div class="w-[10%] text-center p-2">1</div>
                    <div class="w-[20%] text-center p-2 text-center">
                        <input type="checkbox" name="warmup-${exerciseCounter}[]" value="true">
                    </div>
                    <div class="w-[25%] text-center p-2">
                        <input type="number" name="reps-${exerciseCounter}[]" placeholder="12" required
                            class="border px-2 py-1 rounded-md w-full text-center">
                    </div>
                    <div class="w-[25%] text-center p-2">
                        <input type="number" name="weight-${exerciseCounter}[]" placeholder="44" required
                            class="border px-2 py-1 rounded-md w-full text-center">
                    </div>
                    <div class="w-[10%] text-center p-2 text-center">
                        <button type="button" data-exercise="${exerciseCounter}"
                            class="remove-set-btn text-red-500 font-bold">
                            <img src="../icons/trash.svg" style="width: 1rem" />
                        </button>
                    </div>
                    <div class="w-[10%] text-center p-2 text-center">
                        <button type="submit" class="text-red-500 font-bold">
                            <img src="../icons/send-horizontal.svg" style="width: 1rem" />
                        </button>
                    </div>
                </il>
            </ul>
        </section>
    </div>
    <button type="button" data-exercise="${exerciseCounter}" class="add-set-btn bg-blue-500 px-4 py-2 rounded-md">
        + Add Set
    </button>
</div>
`;
    }

    exerciseList.insertAdjacentHTML("beforeend", exerciseHTML);
}

function addSet(exerciseNum) {
    const tableBody = document.getElementById(`setTableBody-${exerciseNum}`);
    const setCount = tableBody.children.length + 1;
    let rowHTML = '';
    if (page === "edit-template" || page === "create-template") {
        rowHTML = `
        <li class="flex flex-row items-center">
            <div class="w-[15%] p-2 text-center">${setCount}</div>
            <div class="w-[20%] text-center p-2">
                <input type="checkbox" name="warmup-${exerciseNum}[]" value="true">
            </div>
            <div class="w-[25%] text-center p-2">
                <input type="number" name="reps-${exerciseNum}[]" placeholder="12" required
                    class="border px-2 py-1 rounded-md w-full text-center">
            </div>
            <div class="w-[25%] text-center p-2">
                <input type="number" name="weight-${exerciseNum}[]" placeholder="44" required
                    class="border px-2 py-1 rounded-md w-full text-center">
            </div>
            <div class="w-[15%] text-center p-2">
                <button type="button" data-exercise="${exerciseNum}" class="remove-set-btn text-red-500 font-bold">
                    <img src="../icons/trash.svg" style="width: 1rem" />
                </button>
            </div>
        </li>
        `;
    tableBody.insertAdjacentHTML("beforeend", rowHTML);
    }

    if (page === "workout") {
    let tbody = document.querySelector(`#setTableBody-${exerciseNum}`);
    let exerciseId = tbody ? tbody.getAttribute("data-exercise-id") : null;
    let newForm = document.createElement("form");

        newForm.setAttribute("hx-post", "/api/v1/sets");
        newForm.setAttribute("hx-trigger", "submit");
        newForm.setAttribute("hx-target", "this");
        newForm.setAttribute("hx-swap", "innerHTML");
        const setPosition = setCount-1;
        newForm.innerHTML = `
<form hx-post="/api/v1/sets" hx-trigger="submit" hx-target="this" hx-swap="innerHTML">
    <li class="flex flex-row items-center">
        <input style="display: none" type="number" name="exerciseNum" required value="${exerciseNum}">
        <input style="display: none" type="number" name="workoutId" required value="${workoutId}">
        <input style="display: none" type="number" name="exerciseId" required value="${exerciseId}">
        <input style="display: none" type="number" name="setNum" required value="${setPosition}">
        <div class="w-[10%] text-center p-2">
            ${setCount}
        </div>
        <div class="w-[20%] text-center p-2">
            <input type="checkbox" name="warmup">
        </div>
        <div class="w-[25%] text-center p-2">
            <input type="number" name="reps" required class="border px-2 py-1 rounded-md w-full text-center"
                placeholder="12">
        </div>
        <div class="w-[25%] text-center p-2">
            <input type="number" name="weight" required class="border px-2 py-1 rounded-md w-full text-center"
                placeholder="20">
        </div>
        <div class="w-[10%] text-center p-2">
            <button type="button" data-exercise="${setCount}" class="remove-set-btn text-red-500 font-bold">
                <img src="../icons/trash.svg" style="width: 1rem" />
            </button>
        </div>
        <div class="w-[10%] text-center p-2">
            <button type="submit" class="text-red-500 font-bold">
                <img src="../icons/send-horizontal.svg" style="width: 1rem" />
            </button>
        </div>
    </li>
</form>
`;
    document.getElementById(`setTableBody-${exerciseNum}`).appendChild(newForm);
    htmx.process(newForm);
    }
}

function prepareDataForHTMX(formElement) {
    const templateId = formElement.querySelector('input[name="templateId"]');
    const workoutData = {
        id: templateId ? templateId.value : null,
        name: formElement.querySelector('input[name="name"]').value,
        description: formElement.querySelector('textarea[name="description"]').value,
        color: formElement.querySelector('input[name="color"]').value,
        unparsedTags: formElement.querySelector('input[name="unparsedTags"]').value,
        sets: []
    };

    formElement.querySelectorAll('.exercise-item').forEach((exerciseElement, index) => {
        const exerciseId = exerciseElement.querySelector(`input[name="exerciseId-${index}"]`).value;
        const sets = [];

        const setRows = exerciseElement.querySelectorAll(`#setTableBody-${index} li`);
        setRows.forEach((row, setIndex) => {
            const newSet = {
                exerciseId: exerciseId,
                reps: row.querySelector(`input[name="reps-${index}[]"]`).value,
                weight: row.querySelector(`input[name="weight-${index}[]"]`).value,
                warmup: row.querySelector(`input[name="warmup-${index}[]"]`).checked
            };

            workoutData.sets.push(newSet);
        });
    });
    return workoutData;
}

document.getElementById("exerciseContainer").addEventListener("click", function (event) {
    const button = event.target.closest("button");

    if (!button) return;

    if (button.classList.contains("add-set-btn")) {
        addSet(button.getAttribute("data-exercise"));
    } else if (button.classList.contains("remove-set-btn")) {
        const row = button.closest("li");
        const tableBody = row.closest("ul");

        row.remove();

        if (Array.from(tableBody.children).length > 0) {
            Array.from(tableBody.children).forEach((tr, index) => {
                tr.children[0].textContent = index + 1;
            });
        }
    } else if (page !== "workout" && button.classList.contains("remove-exercise-btn")) {
        button.closest(".exercise-item").remove();
    }
});

document.getElementById("addExerciseBtn").addEventListener("click", addExercise);

document.body.addEventListener("htmx:beforeRequest", function (event) {
    if (page === "workout") {
        return;
    }
    if (event.detail.elt && event.detail.elt.id === "workoutForm") {
        event.preventDefault();
        const form = event.target;
        if (!form) return;

        fetch('/api/v1/workouts/templates', {
            method: event.detail.requestConfig.verb.toUpperCase(),
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(prepareDataForHTMX(form))
        })
            .then(response => {
                if (response.status === 303) {
                    const redirectUrl = response.headers.get("HX-Redirect");
                    const toastMessage = response.headers.get("X-Message");

                    if (toastMessage) {
                        showToast(toastMessage);
                    }

                    if (redirectUrl) {
                        window.location.href = redirectUrl;
                    }

                    return;
                }

            })
            .then(html => {
                form.outerHTML = html;
            })
            .catch(error => {
                console.error("Request failed:", error);
                if (error.status === 500) {
                    console.error("An error occurred. Please try again.", "error");
                }
            });
    }
});