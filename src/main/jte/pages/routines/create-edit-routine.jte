@param progressive_overlords.entities.dao.WorkoutDao routine
@template.main(tabName = "Create routine",content = @`
    @template.components.nav.sidebar(activeTab = "routines")
    @template.components.routines.routine-form-builder(routine = routine)
`)