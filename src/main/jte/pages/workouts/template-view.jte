@param progressive_overlords.entities.dao.WorkoutDao template

@template.pages.layout.main(tabName = "Template: " + template.getName(),content = @`
    <div id="template-page" class="flex flex-col gap-2 w-full md:w-[600px]">
        <div class="flex flex-row gap-1 justify-between items-center">
            <div class="flex flex-row gap-2 items-center">
                <div class="min-w-[1rem] min-h-[1rem] rounded-md" style="background-color: ${template.getColor()}"></div>
                <h1 class="text-xl font-semibold">${template.getName()}</h1>
            </div>
            <div class="flex flex-row gap-2 items-center">
                <a href="/edit-template/${template.getId()}">
                    <button class="flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100">
                        <img src="../icons/pen.svg" style="width: 1rem"/> Edit
                    </button>
                </a>
                <button hx-swap="none" hx-target="#template-page" hx-delete="/api/v1/workouts/${template.getId()}" class="flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100">
                    <img src="../icons/trash.svg" style="width: 1rem"/> Delete
                </button>
            </div>
        </div>
            <p>
                <span>
                    Description:
                </span>
                ${template.getDescription()}
            </p>

            <p>${template.getUnparsedTags()}</p>

            <div id="exerciseContainer">
                <h3 class="font-bold mt-4">Exercises</h3>
                @if (template != null && template.getExercises() != null)
                    @for(int i = 0; i < template.getExercises().size(); i++)
                        <div class="exercise-item border px-4 py-2 mb-2 rounded-md">
                            <div class="flex flex-row align-center justify-between py-2">
                                <label>Exercise:</label>
                            </div>
                            <h4>${template.getExercises().get(i).getExerciseId()}</h4>
                            <div class="sets-container border-collapse border rounded-md">
                                <section class="w-full ">
                                    <ul class="flex flex-row items-center bg-gray-100">
                                        <li class="w-[15%] text-center p-2">Set</li>
                                        <li class="w-[25%] text-center p-2">Warmup</li>
                                        <li class="w-[30%] text-center p-2">Reps</li>
                                        <li class="w-[30%] text-center p-2">Weight</li>
                                    </ul>
                                    <ul>
                                        @for (progressive_overlords.entities.dao.SetDao set : template.getExercises().get(i).getSets())
                                        <li class="flex flex-row items-center">
                                            <div class="w-[15%] p-2 text-center">
                                                ${set.getSetNum() +1}
                                            </div>
                                            <div class="w-[25%] text-center p-2 text-center">
                                                <input type="checkbox" disabled="true" checked="${set.isWarmup()}">
                                            </div>
                                            <div class="w-[30%] text-center p-2">
                                                <p class="px-2 py-1 w-full text-center">${set.getReps()}</p>
                                            </div>
                                            <div class="w-[30%] text-center p-2">
                                                <p class="px-2 py-1 w-full text-center">${set.getWeight()}</p>
                                            </div>
                                        </li>
                                    @endfor
                                    </ul>
                                </section>
                            </div>
                        </div>
                    @endfor
                @endif
            </div>
    </div>
`)