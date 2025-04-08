@param progressive_overlords.entities.dao.SetDao set
    <form hx-post="${set.isCompleted() ? null : "/api/v1/sets"}" hx-patch="${set.isCompleted() ? "/api/v1/sets" : null}" hx-target="this" hx-swap="outerHTML" id="${set.isCompleted() ? "workout-set-" + set.getId() : null}">
        <ul class="setTableRow">
            <li class="font-bold set-num">
                ${set.getSetNum() +1}
                <input class="hidden" required value="${set.getWorkoutId()}" name="workoutId" />
                <input class="hidden" required value="${set.getExerciseId()}" name="exerciseId" />
                <input class="hidden" required value="${set.getSetNum()}" name="setNum" />
                <input class="hidden" required value="${set.getExerciseNum()}" name="exerciseNum" />
            </li>
            <li>
                <input class="border-none" name="warmup" checked="${set.isWarmup()}" type="checkbox">
            </li>
            <li>
                <input type="number" name="reps" placeholder="${set.getReps()}" value="${set.isCompleted() ? set.getReps() : null}" required>
            </li>
            <li>
                <input type="number" name="weight" placeholder="${set.getWeight()}" value="${set.isCompleted() ? set.getWeight() : null}" required>
            </li>
            <li class="flex-row gap-05">
                @if(set.isCompleted())
                    <button type="button" hx-delete="/api/v1/sets/${set.getId()}" hx-trigger="click" hx-swap="none" class="ghost-button-icon" >
                        <img src="../icons/x.svg" style="width: 1rem" />
                    </button>
                    <button type="submit"  class="ghost-button-icon" >
                        <img src="../icons/check.svg" style="width: 1rem" />
                    </button>
                @else
                    <button type="button"  class="ghost-button-icon remove-set-btn" >
                        <img src="../icons/x.svg" style="width: 1rem" />
                    </button>
                    <button type="submit"  class="ghost-button-icon" >
                        <img src="../icons/send-horizontal.svg" style="width: 1rem" />
                    </button>
                @endif
            </li>
        </ul>
    </form>