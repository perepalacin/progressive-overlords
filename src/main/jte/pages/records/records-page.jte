@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises
@template.main(tabName = "Routines",content = @`
@template.components.nav.sidebar(activeTab = "records")
<div id="workoutForm">
    <div class="flex-row align-center justify-between">
        <h1>Records</h1>
        <div class="flex-row align-center gap-1 desktop-hidden">
            @template.components.exercises.mobile-exercise-sidebar(exercises = exercises)
        </div>
    </div>
    <div class="flex-row gap-2">
        <div class="w-full" id="exercise-data-container">
            <h3>Select an exercise to check your statistics</h3>
        </div>
        <div style="min-width: 18rem" class="flex-column mobile-hidden">
            @template.components.exercises.exercise-sidebar(exercises = exercises)
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/echarts@5.6.0/dist/echarts.min.js"></script>
`)