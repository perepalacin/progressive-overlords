@param progressive_overlords.entities.dao.WorkoutDao workout

@template.main(tabName = workout.getName(),content = @`
    @template.components.workouts.ongoing-workout-view(workout = workout)
`)