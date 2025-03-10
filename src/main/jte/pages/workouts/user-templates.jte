@param java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates

@template.pages.layout.main(tabName = "Routines",content = @`
    <div class="flex flex-col gap-2 w-full md:w-[600px]">
        <div class="flex flex-row w-full justify-between">
            <h1 class="text-xl font-semibold">Your Templates</h1>
            <button>
                <a href="/create-template">
                    Add a new template
                </a>
            </button>
        </div>
        <div class="flex flex-col border rounded-md px-4 py-3 gap-2">
        @if(templates.isEmpty())
            <p>You have no routines at the moment.</p>
        @else
            <ul class="flex flex-col gap-2">
                @for(progressive_overlords.entities.dao.WorkoutDao template : templates)
                    <li id="template-${template.getId()}" class="flex flex-col items-start justify-center border rounded-md pl-6 pr-2 py-2">
                        <div class="flex flex-row relative justify-between w-full items-center">
                            <div class="flex flex-row gap-2 items-center">
                                <div class="w-[1rem] h-[1rem] rounded-md" style="background-color: ${template.getColor()}"></div>
                                <p>${template.getName()}</p>
                            </div>
                            <div>
                                <button class="accordion-button p-2 rounded-full hover:bg-gray-200 transition" data-id="${template.getId()}">
                                    <img src="../icons/chevron-down.svg" class="accordion-icon transition-transform duration-300 ease-in-out" style="width: 1rem;" />
                                </button>
                                <button class="menu-button p-2 rounded-full hover:bg-gray-200 transition" data-id="${template.getId()}">
                                    <img src="../icons/ellipsis-vertical.svg" style="width: 1rem"/>
                                </button>
                                <div class="dropdown-menu hidden absolute top-0 right-0 mt-12 w-40 bg-white border rounded-lg shadow-lg z-50" data-id="${template.getId()}">
                                    <a href="/edit-template/${template.getId()}">
                                        <button class="flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100">
                                            <img src="../icons/pen.svg" style="width: 1rem"/> Edit
                                        </button>
                                    </a>
                                    <button hx-delete="/api/v1/workouts/${template.getId()}" hx-swap="outerHTML" hx-target="#template-${template.getId()}" class="flex items-center gap-2 px-4 py-2 w-full text-left text-red-600 hover:bg-gray-100">
                                        <img src="../icons/trash.svg" style="width: 1rem"/> Delete
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="accordion hidden mt-2 w-full" data-id="${template.getId()}">
                            <div class="mb-2 h-[1px] bg-gray-200"></div>
                            <p class="mb-2 text-md">
                                <span class="font-semibold">Description: </span>
                                ${template.getDescription()}
                            </p>
                            <p>
                                <span class="font-semibold">Tags: </span>
                                ${template.getUnparsedTags()}
                            </p>
                        </div>
                    </li>
                @endfor
            </ul>
        @endif
        </div>
    </div>
    <script src="../scripts/accordion.js"></script>
`)

