@param java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity
<ul class="flex-column gap-1">
    @for (progressive_overlords.entities.dao.WorkoutSummaryDao summary : feedActivity)
        <li>
            <div class="flex-row gap-2 align-center">
                @template.components.friends.user-logo(username = summary.getPublicUserDao().getUsername())
                <div>
                    <p>${summary.getPublicUserDao().getUsername()}</p>
                    <span class="label">${summary.getStartDate()}</span>
                </div>
            </div>
            <p>${summary.getName()}</p>
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
        </li>
    @endfor
</ul>