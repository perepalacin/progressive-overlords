package gg.jte.generated.ondemand.components.workouts;
public final class JteongoingworkoutviewGenerated {
	public static final String JTE_NAME = "components/workouts/ongoing-workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,4,6,6,6,6,15,15,16,16,18,18,27,27,28,28,28,30,30,30,30,30,33,33,33,33,33,33,33,33,33,36,36,36,36,36,36,36,36,36,38,38,42,42,43,43,48,48,48,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout) {
		jteOutput.writeContent("<section class=\"w-full mb-6\">\n    <div id=\"workoutForm\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(workout.getName());
		jteOutput.writeContent("</h1>\n            <div class=\"flex-row align-center gap-1\">\n                <button class=\"button muted-button gap-05\" hx-trigger=\"click\" hx-delete=\"/api/v1/routines/");
		jteOutput.setContext("button", "hx-delete");
		jteOutput.writeUserContent(workout.getId());
		jteOutput.setContext("button", null);
		jteOutput.writeContent("?redirect=true\" hx-target=\"this\" hx-swap=\"none\">\n                    Cancel\n                </button>\n                <button class=\"button main-button gap-05\">\n                    Finish\n                </button>\n            </div>\n        </div>\n        <ul id=\"exerciseContainer\">\n            ");
		if (workout != null && workout.getExercises() != null && !workout.getExercises().isEmpty()) {
			jteOutput.writeContent("\n                ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : workout.getExercises()) {
				jteOutput.writeContent("\n                    <li class=\"exercise-item\">\n                        ");
				gg.jte.generated.ondemand.components.exercises.JteexercisepreviewGenerated.render(jteOutput, jteHtmlInterceptor, exercise.getExercise());
				jteOutput.writeContent("\n                        <section class=\"template-input-grid template-view-grid\">\n                            <ul>\n                                <li>Set</li>\n                                <li>Warmup</li>\n                                <li>Reps</li>\n                                <li>Weight</li>\n                            </ul>\n                            <ul class=\"setTableRow\">\n                                ");
				for (progressive_overlords.entities.dao.SetDao set : exercise.getSets()) {
					jteOutput.writeContent("\n                                    <li class=\"font-bold set-num\">");
					jteOutput.setContext("li", null);
					jteOutput.writeUserContent(set.getSetNum() +1);
					jteOutput.writeContent("</li>\n                                    <li>\n                                        <input class=\"border-none\" name=\"warmups[]\"");
					var __jte_html_attribute_0 = set.isWarmup();
					if (__jte_html_attribute_0) {
					jteOutput.writeContent(" checked");
					}
					jteOutput.writeContent(" type=\"checkbox\">\n                                    </li>\n                                    <li>\n                                        <input type=\"number\" name=\"reps[]\" disabled=\"true\" placeholder=\"12\" required");
					var __jte_html_attribute_1 = set.getReps();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_1);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n                                    </li>\n                                    <li>\n                                        <input type=\"number\" name=\"weights[]\" disabled=\"true\" placeholder=\"44\" required");
					var __jte_html_attribute_2 = set.getWeight();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_2);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" >\n                                    </li>\n                                ");
				}
				jteOutput.writeContent("\n                            </ul>\n                        </section>\n                    </li>\n                ");
			}
			jteOutput.writeContent("\n            ");
		}
		jteOutput.writeContent("\n        </ul>\n    </div>\n</section>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		render(jteOutput, jteHtmlInterceptor, workout);
	}
}
