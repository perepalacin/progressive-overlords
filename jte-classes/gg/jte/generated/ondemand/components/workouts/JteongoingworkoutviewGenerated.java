package gg.jte.generated.ondemand.components.workouts;
public final class JteongoingworkoutviewGenerated {
	public static final String JTE_NAME = "components/workouts/ongoing-workout-view.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,5,5,5,5,6,6,9,9,9,9,13,13,16,16,18,18,20,20,33,33,34,34,35,35,43,43,44,44,46,46,83,83,124,124,144,144,144,177,177,181,181,181,183,183,185,185,185,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout) {
		jteOutput.writeContent("<section class=\"w-full mb-6\">\n    <div id=\"workoutForm\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(workout.getName());
		jteOutput.writeContent("</h1>\n            ");
		if (workout.getEndDate() == null) {
			jteOutput.writeContent("\n            <div class=\"flex-row gap-1 align-center\">\n                <p id=\"timer\">00:00:00</p>\n                <button hx-patch=\"/api/v1/workouts/finish/");
			jteOutput.setContext("button", "hx-patch");
			jteOutput.writeUserContent(workout.getId());
			jteOutput.setContext("button", null);
			jteOutput.writeContent("\" hx-swap=\"none\" hx-trigger=\"click\" class=\"button main-button\" type=\"submit\">\n                    Finish\n                </button>\n            </div>\n            ");
		}
		jteOutput.writeContent("\n        </div>\n        <ul id=\"exerciseContainer\">\n            ");
		if (workout != null && !workout.getExercises().isEmpty()) {
			jteOutput.writeContent("\n\n                ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : workout.getExercises()) {
				jteOutput.writeContent("\n                    <li class=\"exercise-item\">\n                        ");
				gg.jte.generated.ondemand.responses.exercises.JteexerciseheaderGenerated.render(jteOutput, jteHtmlInterceptor, exercise, workout.getId(), exercise.getExercise());
				jteOutput.writeContent("\n                        <section class=\"template-input-grid\">\n                            <ul>\n                                <li>Set</li>\n                                <li>Warmup</li>\n                                <li>Reps</li>\n                                <li>Weight</li>\n                                <li>\n                                    <button type=\"button\"  class=\"button muted-button mobile-hidden add-set-btn\">\n                                        + Add Set\n                                    </button>\n                                </li>\n                            </ul>\n                            ");
				for (progressive_overlords.entities.dao.SetDao set : exercise.getSets()) {
					jteOutput.writeContent("\n                                ");
					gg.jte.generated.ondemand.components.sets.JteworkoutsetGenerated.render(jteOutput, jteHtmlInterceptor, set);
					jteOutput.writeContent("\n                            ");
				}
				jteOutput.writeContent("\n                        </section>\n                        <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0rem 1rem;\">\n                            <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                                + Add Set\n                            </button>\n                        </div>\n                    </li>\n                ");
			}
			jteOutput.writeContent("\n            ");
		} else {
			jteOutput.writeContent("\n                <li class=\"exercise-item\">\n                    ");
			gg.jte.generated.ondemand.responses.exercises.JteexercisesselectorGenerated.render(jteOutput, jteHtmlInterceptor);
			jteOutput.writeContent("\n                    <section class=\"template-input-grid\">\n                        <ul>\n                            <li>Set</li>\n                            <li>Warmup</li>\n                            <li>Reps</li>\n                            <li>Weight</li>\n                            <li>\n                                <button type=\"button\"  class=\"button muted-button mobile-hidden add-set-btn\">\n                                    + Add Set\n                                </button>\n                            </li>\n                        </ul>\n                        <ul class=\"setTableRow\">\n                            <li class=\"font-bold set-num\">1</li>\n                            <li>\n                                <input class=\"border-none\" name=\"warmup\" checked=\"checked\" type=\"checkbox\">\n                            </li>\n                            <li>\n                                <input type=\"number\" name=\"reps\" placeholder=\"12\" required>\n                            </li>\n                            <li>\n                                <input type=\"number\" name=\"weight\" placeholder=\"44\" required >\n                            </li>\n                            <li>\n                                <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                                    <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                                </button>\n                            </li>\n                        </ul>\n                    </section>\n                    <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0rem 1rem;\">\n                        <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                            + Add Set\n                        </button>\n                    </div>\n                </li>\n            ");
		}
		jteOutput.writeContent("\n        </ul>\n        <div class=\"flex-row justify-end\">\n            <button class=\"button main-button\" type=\"button\" id=\"addExerciseBtn\">+ Add Exercise</button>\n        </div>\n    </div>\n</section>\n\n<template id=\"setRowTemplate\">\n    <form hx-post=\"/api/v1/sets\" hx-target=\"this\" hx-swap=\"outerHTML\">\n        <ul class=\"setTableRow\">\n            <li class=\"font-bold set-num\">\n                1\n                <input class=\"hidden\" required value=\"0\" name=\"workoutId\" />\n                <input class=\"hidden\" required value=\"0\" name=\"exerciseId\" />\n                <input class=\"hidden\" required value=\"0\" name=\"setNum\" />\n                <input class=\"hidden\" required value=\"0\" name=\"exerciseNum\" />\n            </li>\n            <li>\n                <input class=\"border-none\" name=\"warmup\" type=\"checkbox\" >\n            </li>\n            <li>\n                <input type=\"number\" name=\"reps\" placeholder=\"12\" required>\n            </li>\n            <li>\n                <input type=\"number\" name=\"weight\" placeholder=\"44\" required >\n            </li>\n            <li>\n                <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                    <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                </button>\n                <button type=\"submit\"  class=\"ghost-button-icon\" >\n                    <img src=\"../icons/send-horizontal.svg\" style=\"width: 1rem\" />\n                </button>\n            </li>\n        </ul>\n    </form>\n</template>\n\n<template id=\"exerciseTemplate\">\n    <li class=\"exercise-item\">\n        ");
		gg.jte.generated.ondemand.responses.exercises.JteexercisesselectorGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n    </li>\n</template>\n\n<template id=\"setTableTemplate\">\n    <section class=\"template-input-grid\">\n        <ul>\n            <li>Set</li>\n            <li>Warmup</li>\n            <li>Reps</li>\n            <li>Weight</li>\n            <li>\n                <button type=\"button\" class=\"button muted-button mobile-hidden add-set-btn\">\n                    + Add Set\n                </button>\n            </li>\n        </ul>\n        <form hx-post=\"/api/v1/sets\" hx-target=\"this\" hx-swap=\"outerHTML\">\n            <ul class=\"setTableRow\">\n                <li class=\"font-bold set-num\">\n                    ");
		jteOutput.setContext("li", null);
		jteOutput.writeUserContent(1);
		jteOutput.writeContent("\n                    <input class=\"hidden\" required value=\"0\" name=\"workoutId\" />\n                    <input class=\"hidden\" required value=\"0\" name=\"exerciseId\" />\n                    <input class=\"hidden\" required value=\"1\" name=\"setNum\" />\n                    <input class=\"hidden\" required value=\"0\" name=\"exerciseNum\" />\n                </li>\n                <li>\n                    <input class=\"border-none\" name=\"warmup\" checked=\"false\" type=\"checkbox\">\n                </li>\n                <li>\n                    <input type=\"number\" name=\"reps\" placeholder=\"12\" required>\n                </li>\n                <li>\n                    <input type=\"number\" name=\"weight\" placeholder=\"25\" required>\n                </li>\n                <li class=\"flex-row gap-05\">\n                    <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                        <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                    </button>\n                    <button type=\"submit\"  class=\"ghost-button-icon\" >\n                        <img src=\"../icons/send-horizontal.svg\" style=\"width: 1rem\" />\n                    </button>\n                </li>\n            </ul>\n        </form>\n    </section>\n    <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0.5rem 1rem;\">\n        <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n            + Add Set\n        </button>\n    </div>\n</template>\n\n");
		if (workout.getEndDate() == null) {
			jteOutput.writeContent("\n<script src=\"../scripts/timer.js\">\n</script>\n<script>\n    startElapsedTimer(\"");
			jteOutput.setContext("script", null);
			jteOutput.writeUserContent(workout.getStartDate());
			jteOutput.writeContent("\");\n</script>\n");
		}
		jteOutput.writeContent("\n<script type=\"module\" src=\"../scripts/create-workout.js\"></script>\n<script type=\"module\" src=\"../scripts/send-template.js\"></script>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		render(jteOutput, jteHtmlInterceptor, workout);
	}
}
