package gg.jte.generated.ondemand.components.workouts;
public final class JteongoingworkoutviewGenerated {
	public static final String JTE_NAME = "components/workouts/ongoing-workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,4,13,13,15,15,17,17,30,30,34,34,34,35,35,35,35,35,35,35,35,35,36,36,36,36,36,36,36,36,36,37,37,37,37,37,37,37,37,37,40,40,40,40,40,43,43,43,43,43,43,43,43,43,46,46,46,46,46,46,46,46,46,58,58,66,66,67,67,69,69,106,106,117,117,117,120,120,120,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout) {
		jteOutput.writeContent("<section class=\"w-full mb-6\">\n    <div id=\"workoutForm\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(workout.getName());
		jteOutput.writeContent("</h1>\n            <div class=\"flex-row gap-1 align-center\">\n                <p id=\"timer\">00:00:00</p>\n                <button class=\"button main-button\" type=\"submit\">\n                    Finish\n                </button>\n            </div>\n        </div>\n        <ul id=\"exerciseContainer\">\n            ");
		if (workout != null && !workout.getExercises().isEmpty()) {
			jteOutput.writeContent("\n\n                ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : workout.getExercises()) {
				jteOutput.writeContent("\n                    <li class=\"exercise-item\">\n                        ");
				gg.jte.generated.ondemand.responses.exercises.JteexerciseheaderGenerated.render(jteOutput, jteHtmlInterceptor, exercise.getExercise());
				jteOutput.writeContent("\n                        <section class=\"template-input-grid\">\n                            <ul>\n                                <li>Set</li>\n                                <li>Warmup</li>\n                                <li>Reps</li>\n                                <li>Weight</li>\n                                <li>\n                                    <button type=\"button\"  class=\"button muted-button mobile-hidden add-set-btn\">\n                                        + Add Set\n                                    </button>\n                                </li>\n                            </ul>\n                            ");
				for (progressive_overlords.entities.dao.SetDao set : exercise.getSets()) {
					jteOutput.writeContent("\n                                <form hx-post=\"/api/v1/sets\" hx-target=\"this\" hx-swap=\"outerHTML\">\n                                    <ul class=\"setTableRow\">\n                                            <li class=\"font-bold set-num\">\n                                                ");
					jteOutput.setContext("li", null);
					jteOutput.writeUserContent(set.getSetNum() +1);
					jteOutput.writeContent("\n                                                <input class=\"hidden\" required");
					var __jte_html_attribute_0 = workout.getId();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" name=\"workoutId\"/>\n                                                <input class=\"hidden\" required");
					var __jte_html_attribute_1 = set.getExerciseId();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_1);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" name=\"exerciseId\"/>\n                                                <input class=\"hidden\" required");
					var __jte_html_attribute_2 = set.getSetNum();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_2);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" name=\"setNum\"/>\n                                            </li>\n                                            <li>\n                                                <input class=\"border-none\" name=\"warmup\"");
					var __jte_html_attribute_3 = set.isWarmup();
					if (__jte_html_attribute_3) {
					jteOutput.writeContent(" checked");
					}
					jteOutput.writeContent(" type=\"checkbox\">\n                                            </li>\n                                            <li>\n                                                <input type=\"number\" name=\"reps\"");
					var __jte_html_attribute_4 = set.getReps();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
						jteOutput.writeContent(" placeholder=\"");
						jteOutput.setContext("input", "placeholder");
						jteOutput.writeUserContent(__jte_html_attribute_4);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" required>\n                                            </li>\n                                            <li>\n                                                <input type=\"number\" name=\"weight\"");
					var __jte_html_attribute_5 = set.getWeight();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
						jteOutput.writeContent(" placeholder=\"");
						jteOutput.setContext("input", "placeholder");
						jteOutput.writeUserContent(__jte_html_attribute_5);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" required>\n                                            </li>\n                                            <li class=\"flex-row gap-05\">\n                                                <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                                                    <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                                                </button>\n                                                <button type=\"submit\"  class=\"ghost-button-icon\" >\n                                                    <img src=\"../icons/send-horizontal.svg\" style=\"width: 1rem\" />\n                                                </button>\n                                            </li>\n                                    </ul>\n                                </form>\n                            ");
				}
				jteOutput.writeContent("\n                        </section>\n                        <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0rem 1rem;\">\n                            <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                                + Add Set\n                            </button>\n                        </div>\n                    </li>\n                ");
			}
			jteOutput.writeContent("\n            ");
		} else {
			jteOutput.writeContent("\n                <li class=\"exercise-item\">\n                    ");
			gg.jte.generated.ondemand.responses.exercises.JteexercisesselectorGenerated.render(jteOutput, jteHtmlInterceptor);
			jteOutput.writeContent("\n                    <section class=\"template-input-grid\">\n                        <ul>\n                            <li>Set</li>\n                            <li>Warmup</li>\n                            <li>Reps</li>\n                            <li>Weight</li>\n                            <li>\n                                <button type=\"button\"  class=\"button muted-button mobile-hidden add-set-btn\">\n                                    + Add Set\n                                </button>\n                            </li>\n                        </ul>\n                        <ul class=\"setTableRow\">\n                            <li class=\"font-bold set-num\">1</li>\n                            <li>\n                                <input class=\"border-none\" name=\"warmups[]\" checked=\"checked\" type=\"checkbox\">\n                            </li>\n                            <li>\n                                <input type=\"number\" name=\"reps[]\" placeholder=\"12\" required>\n                            </li>\n                            <li>\n                                <input type=\"number\" name=\"weights[]\" placeholder=\"44\" required >\n                            </li>\n                            <li>\n                                <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                                    <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                                </button>\n                            </li>\n                        </ul>\n                    </section>\n                    <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0rem 1rem;\">\n                        <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                            + Add Set\n                        </button>\n                    </div>\n                </li>\n            ");
		}
		jteOutput.writeContent("\n        </ul>\n        <div class=\"flex-row justify-end\">\n            <button class=\"button main-button\" type=\"button\" id=\"addExerciseBtn\">+ Add Exercise</button>\n        </div>\n    </div>\n</section>\n\n<script src=\"../scripts/timer.js\">\n</script>\n<script>\n    startElapsedTimer(\"");
		jteOutput.setContext("script", null);
		jteOutput.writeUserContent(workout.getStartDate());
		jteOutput.writeContent("\");\n</script>\n<script type=\"module\" src=\"../scripts/create-template.js\"></script>\n<script type=\"module\" src=\"../scripts/send-template.js\"></script>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		render(jteOutput, jteHtmlInterceptor, workout);
	}
}
