@param progressive_overlords.entities.dao.SetDao set
<form hx-patch="/api/v1/sets" hx-trigger="submit" hx-target="this" hx-swap="outerHTML">
    <tr>
        <td class="p-2 text-center">
            ${set.getSetNum() +1}
            <input style="display: none" type="number" name="exerciseNum" required value="${set.getExerciseNum()}">
            <input style="display: none" type="number" name="workoutId" required value="${set.getWorkoutId()}">
            <input style="display: none" type="number" name="exerciseId" required value="${set.getExerciseId()}">
            <input style="display: none" type="number" name="setNum" required value="${set.getSetNum()}">
        </td>
        <td class="p-2 text-center">
            <input type="checkbox" name="warmup" checked="${set.isWarmup()}">
        </td>
        <td class="p-2">
            <input type="number" name="reps" required class="border px-2 py-1 rounded-md w-full text-center" value="${set.getReps()}" placeholder="${set.getReps()}" >
        </td>
        <td class="p-2">
            <input type="number" name="weight" required class="border px-2 py-1 rounded-md w-full text-center" value="${set.getWeight()}"  placeholder="${set.getReps()}">
        </td>
        <td class="p-2 text-center">
            <button type="button" onclick="removeSet(this)" class="text-red-500 font-bold">
                <img src="../icons/trash.svg" style="width: 1rem" />
            </button>
        </td>
        <td class="p-2 text-center">
            <button type="submit" class="text-red-500 font-bold">
                <img src="../icons/check.svg" style="width: 1rem" />
            </button>
        </td>
    </tr>
</form>