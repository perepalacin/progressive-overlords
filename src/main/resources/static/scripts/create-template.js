import { processExerciseDropdowns } from "./exercise-dropdown.js";

document.addEventListener("DOMContentLoaded", () => {
    function countExercises () {
        const liCount = exerciseList.querySelectorAll(":scope > li").length;
        return liCount;
    }
  
    function addExercise() {
        exerciseCounter++;
        const exerciseNode = document.importNode(exerciseTemplate.content, true);
        const exerciseItem = exerciseNode.querySelector(".exercise-item");
        exerciseList.appendChild(exerciseItem);
        if (exerciseCounter >= 1) {
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

    function removeExercise(exerciseItem) {
        if (!exerciseItem || exerciseCounter < 2) {
            return;
        }
        exerciseItem.remove();
        exerciseCounter--;
        if (exerciseCounter === 1) {
            const remainingDeleteButtons = document.querySelectorAll(".remove-exercise-btn");
            if (remainingDeleteButtons.length > 0) {
                remainingDeleteButtons.forEach((element) => {
                    element.style.display = "none";
                });
            }
        }
    }
  
    function addSet(exerciseItem) {
        const setsSection = exerciseItem.querySelectorAll(".setTableRow");
        let setNum = 0;
        if (!setsSection && setsSection.length > 0) {
            return;
        }
        setNum = setsSection.length + 1;
        const setRowClone = document.importNode(setRowTemplate.content, true);
        const setRowNum = setRowClone.querySelector("li.set-num");
        if (setRowNum) {
            setRowNum.textContent = setNum;
        }
        exerciseItem.querySelector('section.template-input-grid').appendChild(setRowClone);
    }
  
    function removeSet(setItem, exerciseItem) {
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
  
    function updateSetNumbers(exerciseItem) {
        const setsSection = exerciseItem.querySelectorAll(".setTableRow");
        if (!setsSection && setsSection.length > 0) {
            return;
        }
        setsSection.forEach((set, index) => {
            const setNum = set.querySelector("li.set-num");
            setNum.textContent = index + 1;
        });
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
          removeExercise(exerciseItem);
        }

        if (button.classList.contains("add-set-btn")) {
            const exerciseItem = event.target.closest(".exercise-item");
            addSet(exerciseItem);
        }

        if (button.classList.contains("remove-set-btn")) {
            const setItem = event.target.closest(".setTableRow");
            const exerciseItem = event.target.closest(".exercise-item");
            removeSet(setItem, exerciseItem);
        }
      });
      
      
    const exerciseTemplate = document.getElementById("exerciseTemplate");
    const setRowTemplate = document.getElementById("setRowTemplate");
    let exerciseCounter = countExercises() ?? 0;

        if (exerciseTemplate) {
          console.log('Template found in main script!');
        } else {
          console.log('Template not found in main script!');
        }

    processExerciseDropdowns();
});