@param java.util.List<progressive_overlords.entities.dao.WorkoutDao> routines

@template.main(tabName = "Routines",content = @`
    @template.components.nav.sidebar(activeTab = "routines")
    <div id="workoutForm">
        <div class="flex-row align-center justify-between">
            <h1>Your Routines</h1>
            <div class="flex-row align-center gap-1">
                <a href="/create-routine">
                    <button class="button main-button gap-05">
                        + Create new routine
                    </button>
                </a>
            </div>
        </div>
        <div>
            @if(routines.isEmpty())
                <p>You have no routines. Create one to start tracking your workouts.</p>
            @else
                <ul class="routines-list">
                    @for(progressive_overlords.entities.dao.WorkoutDao routine : routines)
                        <li id="routine-${routine.getId()}">
                            <div class="routine-header">
                                    <a href="/routine/${routine.getId()}">
                                        <p class="routine-name">${routine.getName()}</p>
                                    </a>
                                <div class="flex-row gap-025 relative">
                                    @if(routine.getDescription() != null)
                                        <button class="accordion-button ghost-button-icon" data-id="${routine.getId()}">
                                            <img src="../icons/chevron-down.svg" class="accordion-icon" style="width: 1rem"/>
                                        </button>
                                    @endif
                                    <button class="menu-button ghost-button-icon " data-id="${routine.getId()}">
                                        <img src="../icons/ellipsis-vertical.svg" style="width: 1rem"/>
                                    </button>
                                    <div class="dropdown-menu flex-column hidden" data-id="${routine.getId()}">
                                        <button hx-post="/api/v1/workouts/start" class="dropdown-item"><img src="../icons/send-horizontal.svg" style="width: 1rem"/>Start</button>
                                        <a href="/create-routine/${routine.getId()}">
                                            <button class="dropdown-item">
                                                <img src="../icons/pen.svg" style="width: 1rem"/> Edit
                                            </button>
                                        </a>
                                        <button hx-delete="/api/v1/routines/${routine.getId()}?redirect=false" hx-swap="outerHTML" hx-target="#routine-${routine.getId()}" class="dropdown-item delete">
                                            <img src="../icons/trash.svg" style="width: 1rem"/> Delete
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion hidden" data-id="${routine.getId()}">
                                <div class="routine-description-box">
                                @if(routine.getDescription() != null)
                                    <hr class="separator" />
                                    <p>
                                        <span class="font-bold mr-1">Description: </span> ${routine.getDescription()}
                                    </p>
                                @endif
                                </div>
                            </div>
                        </li>
                    @endfor
                </ul>
            @endif
        </div>
    </div>

    <script src="../scripts/dropdowns.js"></script>
    <script src="../scripts/accordion.js"></script>
`)