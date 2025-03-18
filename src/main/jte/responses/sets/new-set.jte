@param progressive_overlords.entities.dao.SetDao set

<form hx-patch="/api/v1/sets" hx-trigger="submit" hx-target="this" hx-swap="outerHTML">
    <div class="flex flex-row items-center">
        <input style="display: none" type="number" name="exerciseNum" required value="${set.getExerciseNum()}">
        <input style="display: none" type="number" name="workoutId" required value="${set.getWorkoutId()}">
        <input style="display: none" type="number" name="exerciseId" required value="${set.getExerciseId()}">
        <input style="display: none" type="number" name="setNum" required value="${set.getSetNum()}">
        <div class="w-[10%] text-center p-2">
            ${set.getSetNum() + 1}
        </div>
        <div class="w-[20%] text-center p-2">
            <input type="checkbox" name="warmup" checked="${set.isWarmup()}">
        </div>
        <div class="w-[25%] text-center p-2">
            <input type="number" name="reps" required class="border px-2 py-1 rounded-md w-full text-center"
                   value="${set.getReps()}" placeholder="${set.getReps()}">
        </div>
        <div class="w-[25%] text-center p-2">
            <input type="number" name="weight" required class="border px-2 py-1 rounded-md w-full text-center"
                   value="${set.getWeight()}" placeholder="${set.getReps()}">
        </div>
        <div class="w-[10%] text-center p-2">
            <button hx-delete="/api/v1/sets/${set.getId()}" hx-target="this" hx-swap="outerHTML"
                    class="text-red-500 font-bold">
                <img src="../icons/trash.svg" style="width: 1rem" />
            </button>
        </div>
        <div class="w-[10%] text-center p-2">
            <button type="submit" class="text-red-500 font-bold">
                <img src="../icons/check.svg" style="width: 1rem" />
            </button>
        </div>
    </div>
</form>