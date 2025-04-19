import { processExerciseDropdowns } from "./exercise-dropdown.js";

document.body.addEventListener("htmx:afterRequest", function(event) {
    const func = event.detail.xhr.getResponseHeader("HX-Trigger");
    const message = event.detail.xhr.getResponseHeader("X-Message");
    if (func && message && func === "removeSet") {
        removeCompletedSet(message); // Message == setId
    }
});

document.body.addEventListener("htmx:afterSwap", function(event) {
    const newElement = event.detail.elt;
    if (event?.detail?.requestConfig?.triggeringEvent?.data) {
        const dropdowns = document.querySelectorAll(".exercises-drop-down-response");
        if (dropdowns && dropdowns.length !== 0) {
            dropdowns.forEach((dropdown, index) => {if (index !== dropdowns.length -1) {dropdown.remove()}});
        }
    }
    console.log(event.detail);
    const requestPath = event.detail.requestConfig.path.split("/");
    const newExerciseId = requestPath[requestPath.length -1];
    const exercises = document.querySelectorAll(".exercise-item");

    const urlSegments = window.location.pathname.split("/");
    const workoutId = urlSegments[urlSegments.length-1];

    let exerciseNum = exercises.length + 1;

    if (newElement.id.startsWith("exercise-")) {
        const exerciseItem = newElement.closest(".exercise-item");
        const setTableRows = exerciseItem.querySelectorAll(".setTableRow");
        const tableTemplate = document.getElementById('setTableTemplate');
        const setTableTemplate = document.importNode(tableTemplate.content, true);
        if (setTableRows && setTableRows.length !== 0) {
            // If we are replacing a previous exercise!
            const setNumLi = setTableRows[0].querySelector("li.set-num");
            const exerciseNumInput = setNumLi.querySelector('input[name="exerciseNum"]');
            const exerciseIdInput = setNumLi.querySelector('input[name="exerciseId"]');
            exerciseNum = exerciseNumInput.value;
            const exerciseId = exerciseIdInput.value;

            fetch('/api/v1/sets/exercise?workoutId=' + workoutId + "&exerciseNum=" + exerciseNum +  '&exerciseId=' + exerciseId, {
                method: "DELETE"
            });
            Array.from(exerciseItem.children).forEach(child => {
                if (!child.classList.contains('exercise-header')) {
                    child.remove();
                }
            });
        }

        const setNumLi = setTableTemplate.querySelector("li.set-num");

        const workoutIdInput = setNumLi.querySelector('input[name="workoutId"]');
        workoutIdInput.value = workoutId;

        const setNumInput = setNumLi.querySelector('input[name="setNum"]');
        setNumInput.value = 1;

        const exerciseNumInput = setNumLi.querySelector('input[name="exerciseNum"]');
        console.log(exerciseNum, exerciseNumInput);
        exerciseNumInput.value = exerciseNum;

        const exerciseIdInput = setNumLi.querySelector('input[name="exerciseId"]');
        exerciseIdInput.value = newExerciseId;

        exerciseItem.appendChild(setTableTemplate);

        const setForms = exerciseItem.querySelectorAll('form');
        if (setForms && setForms.length > 0) {
            setForms.forEach((form) => htmx.process(form));
        }
    }
});

function addSet(exerciseItem) {
    const setsSection = exerciseItem.querySelectorAll(".setTableRow");

    let setNum = 0;
    if (!setsSection || setsSection.length === 0) {
        return;
    }

    const urlSegments = window.location.pathname.split("/");
    const workoutId = urlSegments[urlSegments.length-1];

    const firstSet = setsSection[0];
    const exerciseNum = firstSet.querySelector('input[name="exerciseNum"]');
    const exerciseNumValue = exerciseNum?.value;
    const exerciseId = firstSet.querySelector('input[name="exerciseId"]');
    const exerciseIdValue = exerciseId?.value;

    setNum = setsSection.length + 1;
    const setRowClone = document.importNode(setRowTemplate.content, true);
    const setNumLi = setRowClone.querySelector("li.set-num");
    const workoutIdInput = setNumLi.querySelector('input[name="workoutId"]');
    const setNumInput = setNumLi.querySelector('input[name="setNum"]');
    const exerciseNumInput = setNumLi.querySelector('input[name="exerciseNum"]');
    const exerciseIdInput = setNumLi.querySelector('input[name="exerciseId"]');
    setNumLi.childNodes[0].nodeValue = `${setNum}`;

    if (workoutIdInput) {
        workoutIdInput.value = workoutId;
    }
    if (setNumInput) {
        setNumInput.value = setNum;
    }
    if (exerciseNumValue && exerciseNumInput) {
        exerciseNumInput.value = exerciseNumValue;
    }
    if (exerciseIdValue && exerciseIdInput) {
        exerciseIdInput.value = exerciseIdValue;
    }

    if (!workoutIdInput || !setNumInput || !exerciseNumValue || !exerciseNumInput || !exerciseIdValue || !exerciseIdInput) {
        return;
    }

    exerciseItem.querySelector('section.template-input-grid').appendChild(setRowClone);
    const setForms = exerciseItem.querySelectorAll('form');
        debugger;
    if (setForms && setForms.length > 0) {
        setForms.forEach((form) => htmx.process(form));
    }
}

function updateSetNumbers(exerciseItem) {
    const setsSection = exerciseItem.querySelectorAll(".setTableRow");
    if (!setsSection || setsSection.length === 0) {
        return;
    }

    setsSection.forEach((set, index) => {
        const setNumLi = set.querySelector("li.set-num");
        const setNumInput = setNumLi.querySelector('input[name="setNum"]');
        setNumLi.childNodes[0].nodeValue = `${index + 1} `;

        if (setNumInput) {
            setNumInput.value = index+1;
        }
    });
}

function removeUncompletedSet(setItem, exerciseItem) {
    if (!exerciseItem) {
        return;
    }
    const setCount = exerciseItem.querySelectorAll(".setTableRow");
    if (!setItem || (!setCount || setCount.length < 2)) {
        return;
    }

    setItem.remove();
    if (setCount === 2) {
        const remainingDeleteButtons = exerciseItem.querySelectorAll(".remove-set-btn");
        if (remainingDeleteButtons.length > 0) {
            remainingDeleteButtons.forEach((element) => {
                element.style.display = "none";
            });
        }
    }
    updateSetNumbers(exerciseItem);
}

function removeUnfilledExercise(exerciseItem) {
    if (!exerciseItem || exerciseCounter < 2) {
        return;
    }
    exerciseItem.remove();
}

function removeCompletedSet(setId) {
    const setRow = document.getElementById("workout-set-" + setId);
    if (setRow) {
        const exerciseItem = setRow.closest(".template-input-grid");
        const setCount = exerciseItem.querySelectorAll(".setTableRow");
        setRow.remove();
        updateSetNumbers(exerciseItem);
    }
}

function addExercise() {
    const exercises = document.querySelectorAll(".exercise-item");
    const exerciseNode = document.importNode(exerciseTemplate.content, true);
    const exerciseItem = exerciseNode.querySelector(".exercise-item");

    exerciseList.appendChild(exerciseItem);
    if (exercises && exercises.length >= 1) {
        const remainingDeleteButtons = document.querySelectorAll(".remove-exercise-btn");
        if (remainingDeleteButtons.length > 0) {
            remainingDeleteButtons.forEach((element) => {
            element.style.display = "flex";
            });
        }
    }

    const exerciseSelectors = exerciseList.querySelectorAll(".exercises-drop-down input");
    if (!exerciseSelectors || exerciseSelectors.length == 0) {
        return;
    }
    exerciseSelectors.forEach((selector) => htmx.process(selector));
    processExerciseDropdowns();
}

const exerciseList = document.getElementById("exerciseContainer");
const addExerciseBtn = document.getElementById("addExerciseBtn");
addExerciseBtn.addEventListener("click", addExercise);

exerciseList.addEventListener("click", function(event) {
    const button = event.target.closest("button");

    if (!button) {
        return;
    }

    if (button.classList.contains("remove-exercise-btn")) {
        const exerciseItem = event.target.closest(".exercise-item");
        removeUnfilledExercise(exerciseItem);
    }

    if (button.classList.contains("add-set-btn")) {
        const exerciseItem = event.target.closest(".exercise-item");
        addSet(exerciseItem);
    }

    if (button.classList.contains("remove-set-btn")) {
        const setItem = event.target.closest(".setTableRow");
        const exerciseItem = event.target.closest(".exercise-item");
        removeUncompletedSet(setItem, exerciseItem);
    }
});