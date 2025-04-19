package gg.jte.generated.ondemand.components.workouts;
public final class JtefinishedworkoutviewGenerated {
	public static final String JTE_NAME = "components/workouts/finished-workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,5,5,5,5,7,7,8,8,8,8,14,14,14,14,18,18,21,21,21,25,25,25,29,29,29,34,34,35,35,37,37,46,46,47,47,47,49,49,49,49,49,52,52,52,52,52,52,52,52,52,55,55,55,55,55,55,55,55,55,57,57,61,61,62,62,65,65,65,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout, boolean isEditable) {
		jteOutput.writeContent("<section class=\"w-full mb-6\">\n    <div id=\"workoutForm\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(workout.getName());
		jteOutput.writeContent("</h1>\n            <div class=\"flex-row align-center gap-1\">\n                ");
		if (isEditable) {
			jteOutput.writeContent("\n                <a href=\"/edit-workout/");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(workout.getId());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\">\n                    <button class=\"button main-button gap-05\">\n                        <img src=\"../icons/pen.svg\" style=\"width: 1rem\" />\n                        Edit\n                    </button>\n                </a>\n                <button class=\"button muted-button gap-05\" hx-trigger=\"click\" hx-delete=\"/api/v1/workouts/");
			jteOutput.setContext("button", "hx-delete");
			jteOutput.writeUserContent(workout.getId());
			jteOutput.setContext("button", null);
			jteOutput.writeContent("?redirect=true\" hx-target=\"this\" hx-swap=\"none\">\n                    <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n                    Delete\n                </button>\n                ");
		}
		jteOutput.writeContent("\n            </div>\n        </div>\n        <p>Trained on: <span class=\"font-bold\">");
		jteOutput.setContext("span", null);
		jteOutput.writeUserContent(progressive_overlords.utils.DateFormatter.formatToFriendlyDate(workout.getStartDate()));
		jteOutput.writeContent("</span></p>\n        <div class=\"flex-row gap-2\">\n            <div>\n                <span class=\"label\">Volume:</span>\n                <p class=\"font-bold\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(workout.getAggregatedWorkoutData().getVolume());
		jteOutput.writeContent(" kg</p>\n            </div>\n            <div>\n                <span class=\"label\">Duration:</span>\n                <p class=\"font-bold\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(workout.getAggregatedWorkoutData().getDuration());
		jteOutput.writeContent("</p>\n            </div>\n        </div>\n        <ul id=\"exerciseContainer\">\n            <h3 class=\"m-0\">Exercises</h3>\n            ");
		if (!workout.getExercises().isEmpty()) {
			jteOutput.writeContent("\n                ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : workout.getExercises()) {
				jteOutput.writeContent("\n                    <li class=\"exercise-item\">\n                        ");
				gg.jte.generated.ondemand.components.exercises.JteexercisepreviewGenerated.render(jteOutput, jteHtmlInterceptor, exercise.getExercise());
				jteOutput.writeContent("\n                        <section class=\"template-input-grid template-view-grid\">\n                            <ul>\n                                <li>Set</li>\n                                <li>Warmup</li>\n                                <li>Reps</li>\n                                <li>Weight</li>\n                            </ul>\n                            <ul class=\"setTableRow\">\n                                ");
				for (progressive_overlords.entities.dao.SetDao set : exercise.getSets()) {
					jteOutput.writeContent("\n                                    <li class=\"font-bold set-num\">");
					jteOutput.setContext("li", null);
					jteOutput.writeUserContent(set.getSetNum());
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
		jteOutput.writeContent("\n        </ul>\n    </div>\n</section>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		boolean isEditable = (boolean)params.get("isEditable");
		render(jteOutput, jteHtmlInterceptor, workout, isEditable);
	}
}
