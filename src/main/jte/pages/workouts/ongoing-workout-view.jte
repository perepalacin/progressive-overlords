@param progressive_overlords.entities.dao.WorkoutDao workout

@template.pages.layout.main(tabName = workout.getName(),content = @`
 <div  class="flex flex-col gap-2 w-full md:w-[600px]">
  <div class="flex flex-row items-center justify-between">
   <h1 class="text-xl font-semibold">${workout.getName()}</h1>
   <div class="flex flex-row align-center items-center gap-4">
    <p id="timer">${workout.getStartDate()}</p>
    <button hx-swap="none" hx-patch="/api/v1/workouts/finish/${workout.getId()}" class="flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100">
     Finish Workout
    </button>
   </div>
  </div>
  <div class="flex flex-col gap-2 px-4 py-2 rounded-md border">
   <label class="block font-semibold">Template Name:</label>
   <input type="text" name="name" required class="w-full px-3 py-2 border rounded-md mb-2" placeholder="Enter template name" value="${workout != null ? workout.getName() : ""}">
   <div id="exerciseContainer">
    <h3 class="font-bold mt-4">Exercises</h3>
    @if (workout != null && workout.getExercises() != null)
     @for(int i = 0; i < workout.getExercises().size(); i++)
      <div id="exercise-item-${i}" class="exercise-item-${i} border px-4 py-2 mb-2 rounded-md">
       <div class="flex flex-row align-center justify-between py-2">
        <label>Exercise:</label>
        @template.components.exercises-dropdown(key = i)
        <button hx-delete="/api/v1/sets/exercises?exerciseId=${workout.getExercises().get(i).getExerciseId()}&workoutId=${workout.getId()}" hx-swap="outerHTML" hx-target="#exercise-item-${i}" type="button" data-exercise="${i}" class="remove-exercise-btn text-red-500 font-bold">
         <img src="../icons/trash.svg" style="width: 1rem" />
        </button>
       </div>
       <input type="number" name="exerciseId-${i}" required class="border px-2 py-1 rounded-md w-full mb-2" value="${workout.getExercises().get(i).getExerciseId()}">
       <div class="sets-container border-collapse border rounded-md" data-exercise="${i}">
        <section class="w-full">
         <ul class="flex flex-row items-center bg-gray-100">
          <li class="w-[10%] text-center p-2">Set</li>
          <li class="w-[20%] text-center p-2">Warmup</li>
          <li class="w-[25%] text-center p-2">Reps</li>
          <li class="w-[25%] text-center p-2">Weight</li>
          <li class="w-[10%] text-center p-2"></li>
          <li class="w-[10%] text-center p-2"></li>
         </ul>
         <ul id="setTableBody-${i}" data-exercise-id="${workout.getExercises().get(i).getExerciseId()}">
         @for (progressive_overlords.entities.dao.SetDao set : workout.getExercises().get(i).getSets())
          <form hx-post="${set.isCompleted() ? null : "/api/v1/sets"}" hx-patch="${set.isCompleted() ? "/api/v1/sets": null}" hx-trigger="submit" hx-target="this" hx-swap="innerHTML">
           <li class="flex flex-row items-center">

            @if(set.getId() != 0)
             <input style="display: none" type="number" name="id" required value="${set.getId()}">
            @endif
            <input style="display: none" type="number" name="exerciseNum" required value="${set.getExerciseNum()}">
            <input style="display: none" type="number" name="workoutId" required value="${workout.getId()}">
            <input style="display: none" type="number" name="exerciseId" required value="${set.getExerciseId()}">
            <input style="display: none" type="number" name="setNum" required value="${set.getSetNum()}">

            <div class="w-[10%] text-center p-2">
             ${set.getSetNum() +1}
            </div>
            <div class="w-[20%] text-center p-2">
             <input type="checkbox" name="warmup" checked="${set.isWarmup()}">
            </div>
            <div class="w-[25%] text-center p-2">
             <input type="number" name="reps" required class="border px-2 py-1 rounded-md w-full text-center" value="${set.isCompleted() ? set.getReps() : null}" placeholder="${set.getReps()}" >
            </div>
            <div class="w-[25%] text-center p-2">
             <input type="number" name="weight" required class="border px-2 py-1 rounded-md w-full text-center" value="${set.isCompleted() ? set.getWeight() : null}"  placeholder="${set.getReps()}">
            </div>
            <div class="w-[10%] text-center p-2 text-center">
             @if (set.isCompleted())
             <button hx-delete="/api/v1/sets/${set.getId()}" hx-target="this" hx-swap="innerHTML" class="text-red-500 font-bold">
              <img src="../icons/trash.svg" style="width: 1rem" />
             </button>
             @endif
            </div>
            <div class="w-[10%] text-center p-2 text-center">
             <button type="submit" class="text-red-500 font-bold">
              @if(set.isCompleted())
               <img src="../icons/check.svg" style="width: 1rem" />
              @else
               <img src="../icons/send-horizontal.svg" style="width: 1rem" />
              @endif
             </button>
            </div>
           </li>
          </form>
         @endfor
         </ul>
        </section>
       </div>
       <button type="button" data-exercise="${i}" class="add-set-btn bg-blue-500 px-4 py-2 rounded-md">
        + Add Set
       </button>
      </div>
     @endfor

    @endif
    <div id="exerciseList"></div>
   </div>

   <button type="button" id="addExerciseBtn">+ Add Exercise</button>


  </div>
 </div>
 <script src="../scripts/timer.js"></script>
 <script>
  startElapsedTimer("${workout.getStartDate()}");
 </script>
 <script type="module" src="../scripts/add-exercise.js"></script>
 <script src="https://unpkg.com/htmx-ext-json-enc@2.0.1/json-enc.js"></script>


`)

