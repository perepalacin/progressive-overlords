@param progressive_overlords.entities.dao.SetDao set
<form hx-post="/api/v1/sets" hx-target="this" hx-swap="outerHTML">
    <ul class="setTableRow">
        <li class="font-bold set-num">
            ${set.getSetNum() +1}
            <input class="hidden" required value="${set.getWorkoutId()}" name="workoutId"/>
            <input class="hidden" required value="${set.getExerciseId()}" name="exerciseId"/>
            <input class="hidden" required value="${set.getSetNum()}" name="setNum"/>
        </li>
        <li>
            <input class="border-none" name="warmup" checked="${set.isWarmup()}" type="checkbox">
        </li>
        <li>
            <input type="number" name="reps" placeholder="${set.getReps()}" required>
        </li>
        <li>
            <input type="number" name="weight" placeholder="${set.getWeight()}" required>
        </li>
        <li class="flex-row gap-05">
            <button type="button"  class="ghost-button-icon remove-set-btn" >
                <img src="../icons/x.svg" style="width: 1rem" />
            </button>
            <button type="submit"  class="ghost-button-icon" >
                <img src="../icons/send-horizontal.svg" style="width: 1rem" />
            </button>
        </li>
    </ul>
</form>