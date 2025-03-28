@template.main(tabName = "Home",content = @`
    @template.components.nav.sidebar(activeTab = "routines")
    @template.components.routines.routine-form-builder()
`)