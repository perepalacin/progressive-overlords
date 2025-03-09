@param java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates

@template.pages.layout.main(tabName = "Routines",content = @`
    <div class="flex flex-col gap-2">
        <h1 class="text-xl font-semibold">Your Routines</h1>
        <div class="flex flex-col border rounded-md px-4 py-3 gap-2">
        @if(templates.isEmpty())
            <p>You have no routines at the moment.</p>
            <button>
                Add a new template
            </button>
        @else
            <ul class="flex flex-col gap-2">
                @for(progressive_overlords.entities.dao.WorkoutDao template : templates)
                    <li>
                        <p>${template.getName()}</p>
                    </li>
                @endfor
            </ul>
        @endif
        </div>
    </div>

`)

