package gg.jte.generated.ondemand.components.routines;
public final class JteroutineviewGenerated {
	public static final String JTE_NAME = "components/routines/routine-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,4,6,6,6,6,12,12,12,12,19,19,19,22,22,23,23,25,25,34,34,35,35,35,37,37,37,37,37,40,40,40,40,40,40,40,40,40,43,43,43,43,43,43,43,43,43,45,45,49,49,50,50,55,55,55,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao routine) {
		jteOutput.writeContent("<section class=\"w-full mb-6\">\n    <div id=\"workoutForm\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(routine.getName());
		jteOutput.writeContent("</h1>\n            <div class=\"flex-row align-center gap-1\">\n                <a href=\"/create-routine/");
		jteOutput.setContext("a", "href");
		jteOutput.writeUserContent(routine.getId());
		jteOutput.setContext("a", null);
		jteOutput.writeContent("\">\n                    <button class=\"button main-button gap-05\">\n                        <img src=\"../icons/pen.svg\" style=\"width: 1rem\" />\n                        Edit\n                    </button>\n                </a>\n                <button class=\"button muted-button gap-05\" hx-trigger=\"click\" hx-delete=\"/api/v1/routines/");
		jteOutput.setContext("button", "hx-delete");
		jteOutput.writeUserContent(routine.getId());
		jteOutput.setContext("button", null);
		jteOutput.writeContent("?redirect=true\" hx-target=\"this\" hx-swap=\"none\">\n                    <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n                    Delete\n                </button>\n            </div>\n        </div>\n        <label class=\"block font-semibold\">Description:</label>\n        <p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(routine.getDescription());
		jteOutput.writeContent("</p>\n        <ul id=\"exerciseContainer\">\n            <h3 class=\"m-0\">Exercises</h3>\n            ");
		if (routine != null && !routine.getExercises().isEmpty()) {
			jteOutput.writeContent("\n                ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : routine.getExercises()) {
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
		progressive_overlords.entities.dao.WorkoutDao routine = (progressive_overlords.entities.dao.WorkoutDao)params.get("routine");
		render(jteOutput, jteHtmlInterceptor, routine);
	}
}
