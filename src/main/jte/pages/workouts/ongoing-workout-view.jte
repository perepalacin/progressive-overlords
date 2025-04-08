@param progressive_overlords.entities.dao.WorkoutDao workout

@template.main(tabName = workout.getName(),content = @`
    @template.components.nav.sidebar(activeTab = "routines")
    @template.components.workouts.ongoing-workout-view(workout = workout)
`)