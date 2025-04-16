@param progressive_overlords.entities.dao.WorkoutDao workout
@param boolean isFinished
@param boolean isEditable

@template.main(tabName = workout.getName(),content = @`
    @template.components.nav.sidebar(activeTab = "routines")
    @if(isFinished)
        @template.components.workouts.finished-workout-view(workout = workout, isEditable = isEditable)
    @else
        @template.components.workouts.ongoing-workout-view(workout = workout)
    @endif
`)