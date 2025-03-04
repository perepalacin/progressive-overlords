@param progressive_overlords.entities.dao.WorkoutDao workoutDao

@if(workoutDao != null)
<p>${workoutDao.getName()}</p>
@else
 <p>Give a name to your workout!</p>
@endif

