@param progressive_overlords.entities.dao.WorkoutDao routine

@template.main(tabName = routine.getName(),content = @`
    @template.components.nav.sidebar(activeTab = "routines")
    @template.components.routines.routine-view(routine = routine)
`)