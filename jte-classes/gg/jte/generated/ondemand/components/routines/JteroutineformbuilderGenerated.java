package gg.jte.generated.ondemand.components.routines;
public final class JteroutineformbuilderGenerated {
	public static final String JTE_NAME = "components/routines/routine-form-builder.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,9,9,10,10,10,10,10,10,10,10,10,11,11,13,13,13,13,13,13,13,13,13,15,15,15,19,19,20,20,22,22,36,36,37,37,37,39,39,39,39,39,42,42,42,42,42,42,42,42,42,45,45,45,45,45,45,45,45,45,52,52,61,61,62,62,64,64,101,101,131,131,170,170,170,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao routine) {
		jteOutput.writeContent("<section class=\"w-full mb-6\">\n    <form id=\"workoutForm\" hx-trigger=\"submit\"");
		var __jte_html_attribute_0 = routine == null ? "/api/v1/routines" : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" hx-post=\"");
			jteOutput.setContext("form", "hx-post");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("form", null);
			jteOutput.writeContent("\"");
		}
		var __jte_html_attribute_1 = routine != null ? "/api/v1/routines" : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" hx-patch=\"");
			jteOutput.setContext("form", "hx-patch");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("form", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" hx-swap=\"none\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>Create a template</h1>\n            <button class=\"button main-button\" type=\"submit\">\n                Submit\n            </button>\n        </div>\n        ");
		if (routine != null) {
			jteOutput.writeContent("\n            <input id=\"workout-id-input\"");
			var __jte_html_attribute_2 = routine.getId();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
				jteOutput.writeContent(" value=\"");
				jteOutput.setContext("input", "value");
				jteOutput.writeUserContent(__jte_html_attribute_2);
				jteOutput.setContext("input", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(" class=\"hidden\" >\n        ");
		}
		jteOutput.writeContent("\n        <label>Routine Name:</label>\n        <input type=\"text\" name=\"name\" required placeholder=\"Enter routine name\" class=\"mb-1\"");
		var __jte_html_attribute_3 = routine != null ? routine.getName() : "";
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_3);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n        <label class=\"block font-semibold\">Description:</label>\n        <textarea maxlength=\"511\" rows=\"3\" type=\"text\" class=\"mb-1\" name=\"description\"placeholder=\"Enter description\">");
		jteOutput.setContext("textarea", null);
		jteOutput.writeUserContent(routine != null ? routine.getDescription() : null);
		jteOutput.writeContent("</textarea>\n        <input class=\"hidden\" type=\"checkbox\" checked=\"true\" name=\"isTemplate\" />\n        <ul id=\"exerciseContainer\">\n            <h3 class=\"m-0\">Exercises</h3>\n            ");
		if (routine != null && !routine.getExercises().isEmpty()) {
			jteOutput.writeContent("\n                ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : routine.getExercises()) {
				jteOutput.writeContent("\n                    <li class=\"exercise-item\">\n                        ");
				gg.jte.generated.ondemand.responses.exercises.JteexerciseheaderGenerated.render(jteOutput, jteHtmlInterceptor, exercise.getExercise());
				jteOutput.writeContent("\n                        <section class=\"template-input-grid\">\n                            <ul>\n                                <li>Set</li>\n                                <li>Warmup</li>\n                                <li>Reps</li>\n                                <li>Weight</li>\n                                <li>\n                                    <button type=\"button\"  class=\"button muted-button mobile-hidden add-set-btn\">\n                                        + Add Set\n                                    </button>\n                                </li>\n                            </ul>\n                            <ul class=\"setTableRow\">\n                                ");
				for (progressive_overlords.entities.dao.SetDao set : exercise.getSets()) {
					jteOutput.writeContent("\n                                <li class=\"font-bold set-num\">");
					jteOutput.setContext("li", null);
					jteOutput.writeUserContent(set.getSetNum());
					jteOutput.writeContent("</li>\n                                <li>\n                                    <input class=\"border-none\" name=\"warmups[]\"");
					var __jte_html_attribute_4 = set.isWarmup();
					if (__jte_html_attribute_4) {
					jteOutput.writeContent(" checked");
					}
					jteOutput.writeContent(" type=\"checkbox\">\n                                </li>\n                                <li>\n                                    <input type=\"number\" name=\"reps[]\" placeholder=\"12\" required");
					var __jte_html_attribute_5 = set.getReps();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_5);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n                                </li>\n                                <li>\n                                    <input type=\"number\" name=\"weights[]\" placeholder=\"44\" required");
					var __jte_html_attribute_6 = set.getWeight();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_6)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_6);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" >\n                                </li>\n                                <li>\n                                    <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                                        <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                                    </button>\n                                </li>\n                                ");
				}
				jteOutput.writeContent("\n                            </ul>\n                        </section>\n                        <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0rem 1rem;\">\n                            <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                                + Add Set\n                            </button>\n                        </div>\n                    </li>\n                ");
			}
			jteOutput.writeContent("\n            ");
		} else {
			jteOutput.writeContent("\n            <li class=\"exercise-item\">\n                ");
			gg.jte.generated.ondemand.responses.exercises.JteexercisesselectorGenerated.render(jteOutput, jteHtmlInterceptor);
			jteOutput.writeContent("\n                <section class=\"template-input-grid\">\n                    <ul>\n                        <li>Set</li>\n                        <li>Warmup</li>\n                        <li>Reps</li>\n                        <li>Weight</li>\n                        <li>\n                            <button type=\"button\"  class=\"button muted-button mobile-hidden add-set-btn\">\n                                + Add Set\n                            </button>\n                        </li>\n                    </ul>\n                    <ul class=\"setTableRow\">\n                        <li class=\"font-bold set-num\">1</li>\n                        <li>\n                            <input class=\"border-none\" name=\"warmups[]\" checked=\"checked\" type=\"checkbox\">\n                        </li>\n                        <li>\n                            <input type=\"number\" name=\"reps[]\" placeholder=\"12\" required>\n                        </li>\n                        <li>\n                            <input type=\"number\" name=\"weights[]\" placeholder=\"44\" required >\n                        </li>\n                        <li>\n                            <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                                <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                            </button>\n                        </li>\n                    </ul>\n                </section>\n                <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0rem 1rem;\">\n                    <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                        + Add Set\n                    </button>\n                </div>\n            </li>\n            ");
		}
		jteOutput.writeContent("\n        </ul>\n        <div class=\"flex-row justify-end\">\n            <button class=\"button main-button\" type=\"button\" id=\"addExerciseBtn\">+ Add Exercise</button>\n        </div>\n    </form>\n</section>\n\n<template id=\"setRowTemplate\">\n    <ul class=\"setTableRow\">\n        <li class=\"font-bold set-num\">1</li>\n        <li>\n            <input class=\"border-none\" name=\"warmups[]\" type=\"checkbox\" >\n        </li>\n        <li>\n            <input type=\"number\" name=\"reps[]\" placeholder=\"12\" required>\n        </li>\n        <li>\n            <input type=\"number\" name=\"weights[]\" placeholder=\"44\" required >\n        </li>\n        <li>\n            <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n            </button>\n        </li>\n    </ul>\n</template>\n\n<template id=\"exerciseTemplate\">\n    <li class=\"exercise-item\">\n            ");
		gg.jte.generated.ondemand.responses.exercises.JteexercisesselectorGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n        <section class=\"template-input-grid\">\n            <ul>\n                <li>Set</li>\n                <li>Warmup</li>\n                <li>Reps</li>\n                <li>Weight</li>\n                <li>\n                    <button type=\"button\" class=\"button muted-button mobile-hidden add-set-btn\">\n                        + Add Set\n                    </button>\n                </li>\n            </ul>\n            <ul class=\"setTableRow\">\n                <li class=\"font-bold set-num\">1</li>\n                <li>\n                    <input class=\"border-none\" name=\"warmups[]\" type=\"checkbox\">\n                </li>\n                <li>\n                    <input type=\"number\" name=\"reps[]\" placeholder=\"12\" required>\n                </li>\n                <li>\n                    <input type=\"number\" name=\"weights[]\" placeholder=\"44\" required >\n                </li>\n                <li>\n                    <button type=\"button\" class=\"ghost-button-icon remove-set-btn\" >\n                        <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                    </button>\n                </li>\n            </ul>\n        </section>\n        <div class=\"desktop-hidden w-full mb-1\" style=\"padding: 0.5rem 1rem;\">\n            <button type=\"button\" class=\"button muted-button w-full add-set-btn\">\n                + Add Set\n            </button>\n        </div>\n    </li>\n</template>\n<script type=\"module\" src=\"../scripts/create-template.js\"></script>\n<script type=\"module\" src=\"../scripts/send-template.js\"></script>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao routine = (progressive_overlords.entities.dao.WorkoutDao)params.get("routine");
		render(jteOutput, jteHtmlInterceptor, routine);
	}
}
