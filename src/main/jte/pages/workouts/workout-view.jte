@param progressive_overlords.entities.dao.WorkoutDao workout

@if(workout != null)
<p>${workout.getName()}</p>
 <p>It has a total of ${workout.getExercises().size()} exercises</p>
@else
 <p>Give a name to your workout!</p>
@endif

