@param java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises
<button id="open-mobile-exercise-sidebar-button" class="mobile-exercise-sidebar-toggle-button button main-button" aria-controls="exercise-sidebar" aria-expanded="false">
    <span class="open-icon">Select Exercise</span>
</button>

<div id="exercise-sidebar" class="mobile-exercise-sidebar mobile-exercise-sidebar-hidden" style="min-width: 18rem">
    <div class="flex-column gap-1" style="height: 100%;">
        <button id="close-mobile-exercise-sidebar-button" style="align-self: end;" class="ghost-button-icon mobile-exercise-sidebar-toggle-button" aria-controls="exercise-sidebar" aria-expanded="true">
            <img src="../icons/x.svg" style="width: 1rem" />
        </button>
        @template.components.exercises.exercise-sidebar(exercises = exercises)
    </div>
</div>
<script src="../scripts/exercise-sidebar.js"></script>
