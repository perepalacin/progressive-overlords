@param java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> workoutHistory
@template.main(tabName = "History",content = @`
    @template.components.nav.sidebar(activeTab = "history")
    <div class="flex-row w-full justify-between">
        <div class="px-2 py-1 flex-column w-full align-center">
            <h1 style="align-self: start">Workout History</h1>
            <div class="px-2 py-1 flex-column w-full" style="max-width: 800px">
                @template.components.feeds.feed-activity(feedActivity = workoutHistory, isOwnWorkout = true, newPage = 1)
            </div>
        </div>
    </div>
`)

