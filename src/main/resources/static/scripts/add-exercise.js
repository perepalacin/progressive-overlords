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

 function prepareDataForHTMX(formElement) {
     const workoutData = {
         name: formElement.querySelector('input[name="name"]').value,
         description: formElement.querySelector('textarea[name="description"]').value,
         color: formElement.querySelector('input[name="color"]').value,
         bodyPart: formElement.querySelector('input[name="bodyPart"]').value,
         unparsedTags: formElement.querySelector('input[name="unparsedTags"]').value,
         sets: []
     };

     formElement.querySelectorAll('.exercise-item').forEach((exerciseElement, index) => {
         const exerciseId = exerciseElement.querySelector(`input[name="exerciseId-${index + 1}"]`).value;
         const sets = [];

         const setRows = exerciseElement.querySelectorAll(`#setTableBody-${index + 1} tr`);
         setRows.forEach((row, setIndex) => {
             const newSet = {
                 exerciseId: exerciseId,
                 reps: row.querySelector(`input[name="reps-${index + 1}[]"]`).value,
                 weight: row.querySelector(`input[name="weight-${index + 1}[]"]`).value,
                 warmup: row.querySelector(`input[name="warmup-${index + 1}[]"]`).checked
             };

             workoutData.sets.push(newSet);
         });
     });

     return workoutData;
 }

document.body.addEventListener("htmx:beforeRequest", function (event) {
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
            if (response.ok) {
                return response.text();
            } else {
                return response.json().then(errData => {
                    throw { status: response.status, data: errData };
                });
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

