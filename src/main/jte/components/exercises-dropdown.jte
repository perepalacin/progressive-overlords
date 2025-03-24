<%--@param progressive_overlords.entities.dao.ExerciseDao exercise--%>
@param int key
<div class="relative w-64">
    <input type="search" id="dropdownInput-${key}"
           name="query"
           autocomplete="off"
           hx-trigger="input changed delay:500ms, keyup[key=='Enter'], click"
           hx-get="/api/v1/exercises?page=0&inputId=dropdownList-${key}"
           class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
           placeholder="Select an exercise"
           hx-target="#dropdownList-${key}"
           hx-swap="innerHTML"
    >
    <div id="dropdownList-${key}"
         class="absolute w-full bg-white border border-gray-300 rounded-md shadow-md mt-1 max-h-40 overflow-y-auto">
    </div>
</div>