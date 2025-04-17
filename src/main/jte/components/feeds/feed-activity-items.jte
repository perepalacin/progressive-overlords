@param java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity
@param boolean isOwnWorkout
@param int newPage
@for (progressive_overlords.entities.dao.WorkoutSummaryDao summary : feedActivity)
    <li>
        <a class="feed-item" href="/workout/${summary.getWorkoutId()}">
            @if(!isOwnWorkout)
                <div class="flex-row gap-2 align-center">
                    @template.components.friends.user-logo(username = summary.getPublicUserDao().getUsername())
                    <div>
                        <h3 class="m-0 font-normal">${summary.getPublicUserDao().getUsername()}</h3>
                        <span class="label">${progressive_overlords.utils.DateFormatter.formatToFriendlyDate(summary.getStartDate())}</span>
                    </div>
                </div>
            @endif
            <h2 class="my-025">${summary.getName()}</h2>
            @if(isOwnWorkout)
                <span class="label">${progressive_overlords.utils.DateFormatter.formatToFriendlyDate(summary.getStartDate())}</span>
            @endif
            <div class="flex-row gap-2">
                <div>
                    <span class="label">Volume:</span>
                    <p class="font-bold">${summary.getVolume()} kg</p>
                </div>
                <div>
                    <span class="label">Duration:</span>
                    <p class="font-bold">${progressive_overlords.utils.TimeDiffHelper.getTimeDifference(summary.getDuration())}</p>
                </div>
            </div>
            @for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : summary.getWorkoutExercises())
                <div class="exercise-dropdown-item">
                    <img loading="lazy" src="${exercise.getExercise().getThumbnail()}" />
                    <p>${exercise.getSetsCount()} sets - ${exercise.getExercise().getName()}</p>
                </div>
            @endfor
        </a>
    </li>
@endfor
@if(feedActivity.size() == 4)
    <div style="height: 1rem;" hx-get="/api/v1/history?page=${newPage}" hx-target="this" hx-trigger="revealed" hx-swap="outerHTML"></div>
@endif