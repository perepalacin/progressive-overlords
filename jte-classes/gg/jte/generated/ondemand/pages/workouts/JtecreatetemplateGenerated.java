package gg.jte.generated.ondemand.pages.workouts;
public final class JtecreatetemplateGenerated {
	public static final String JTE_NAME = "pages/workouts/create-template.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,6,7,7,7,7,7,7,7,7,7,8,8,11,11,11,11,11,11,11,11,11,14,14,14,18,18,18,18,18,18,18,18,18,23,23,23,23,23,23,23,23,23,27,27,28,28,36,36,36,36,36,36,36,36,36,36,36,36,36,38,38,38,38,38,38,38,38,38,49,49,49,49,50,50,52,52,52,54,54,54,54,54,54,54,54,54,54,54,54,54,57,57,57,57,57,57,57,57,57,57,57,57,57,60,60,60,60,60,60,60,60,60,68,68,72,72,72,72,76,76,78,78,85,85,85,93,93,93,93,93,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao template) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.pages.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div  class=\"flex flex-col gap-2 w-full md:w-[600px]\">\n    <h1 class=\"text-xl font-semibold\">");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(template != null ? "Edit template" : "Create a template");
				jteOutput.writeContent("</h1>\n    <form id=\"workoutForm\"");
				var __jte_html_attribute_0 = template == null ? "/api/v1/workouts/templates" : null;
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" hx-post=\"");
					jteOutput.setContext("form", "hx-post");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				var __jte_html_attribute_1 = template != null ? "/api/v1/workouts/templates" : null;
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" hx-patch=\"");
					jteOutput.setContext("form", "hx-patch");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" hx-trigger=\"submit\" hx-target=\"this\" hx-swap=\"outerHTML\" class=\"flex flex-col gap-2 px-4 py-2 rounded-md border\">\n        ");
				if (template != null) {
					jteOutput.writeContent("\n            <input style=\"display: none\" type=\"number\" name=\"templateId\" required");
					var __jte_html_attribute_2 = template.getId();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_2);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n        ");
				}
				jteOutput.writeContent("\n\n        <label class=\"block font-semibold\">Template Name:</label>\n        <input type=\"text\" name=\"name\" required class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"Enter template name\"");
				var __jte_html_attribute_3 = template != null ? template.getName() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_3);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n\n        <label class=\"block font-semibold\">Description:</label>\n        <textarea maxlength=\"511\" rows=\"3\" type=\"text\" name=\"description\" class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"Enter description\">");
				jteOutput.setContext("textarea", null);
				jteOutput.writeUserContent(template != null ? template.getDescription() : "");
				jteOutput.writeContent("</textarea>\n\n        <label class=\"block font-semibold\">Color:</label>\n        <div class=\"relative flex items-center gap-2\">\n            <input type=\"color\" name=\"color\" class=\"h-10 w-10 px-1 py-1 border rounded-md cursor-pointer\" id=\"colorPicker\"");
				var __jte_html_attribute_4 = template != null ? template.getColor() : "#00FF00";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_4);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n            <span id=\"colorHex\" class=\"font-mono text-sm\"></span>\n        </div>\n\n        <label class=\"block font-semibold\">Tags (comma-separated):</label>\n        <input type=\"text\" name=\"unparsedTags\" class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"e.g. strength, endurance\"");
				var __jte_html_attribute_5 = template != null ? template.getUnparsedTags() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_5);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n\n        <div id=\"exerciseContainer\">\n            <h3 class=\"font-bold mt-4\">Exercises</h3>\n            ");
				if (template != null && template.getExercises() != null) {
					jteOutput.writeContent("\n                ");
					for (int i = 0; i < template.getExercises().size(); i++) {
						jteOutput.writeContent("\n                        <div class=\"exercise-item border px-4 py-2 mb-2 rounded-md\">\n                            <div class=\"flex flex-row align-center justify-between py-2\">\n                                <label>Exercise:</label>\n                                <button type=\"button\" onclick=\"removeExercise(this)\" class=\"text-red-500 font-bold\">\n                                    <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n                                </button>\n                            </div>\n                            <input type=\"number\" name=\"exerciseId-");
						jteOutput.setContext("input", "name");
						jteOutput.writeUserContent(i);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\" required class=\"border px-2 py-1 rounded-md w-full mb-2\"");
						var __jte_html_attribute_6 = template.getExercises().get(i).getExerciseId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_6)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("input", "value");
							jteOutput.writeUserContent(__jte_html_attribute_6);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n\n                            <div class=\"sets-container border-collapse border rounded-md\"");
						var __jte_html_attribute_7 = i;
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_7)) {
							jteOutput.writeContent(" data-exercise=\"");
							jteOutput.setContext("div", "data-exercise");
							jteOutput.writeUserContent(__jte_html_attribute_7);
							jteOutput.setContext("div", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                <table class=\"w-full \">\n                                    <thead class=\"bg-gray-100\">\n                                    <tr>\n                                        <th class=\"p-2\">Sets</th>\n                                        <th class=\"p-2\">Reps</th>\n                                        <th class=\"p-2\">Weight</th>\n                                        <th class=\"p-2\">Warmup</th>\n                                        <th class=\"p-2\"></th>\n                                    </tr>\n                                    </thead>\n                                    <tbody id=\"setTableBody-");
						jteOutput.setContext("tbody", "id");
						jteOutput.writeUserContent(i);
						jteOutput.setContext("tbody", null);
						jteOutput.writeContent("\">\n                                    ");
						for (progressive_overlords.entities.dao.SetDao set : template.getExercises().get(i).getSets()) {
							jteOutput.writeContent("\n                                    <tr>\n                                        <td class=\"p-2 text-center\">");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(set.getSetNum() +1);
							jteOutput.writeContent("</td>\n                                        <td class=\"p-2\">\n                                            <input type=\"number\" name=\"reps-");
							jteOutput.setContext("input", "name");
							jteOutput.writeUserContent(i);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("[]\" placeholder=\"12\" required class=\"border px-2 py-1 rounded-md w-full text-center\"");
							var __jte_html_attribute_8 = set.getReps();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_8)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_8);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n                                        </td>\n                                        <td class=\"p-2\">\n                                            <input type=\"number\" name=\"weight-");
							jteOutput.setContext("input", "name");
							jteOutput.writeUserContent(i);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("[]\" placeholder=\"44\" required class=\"border px-2 py-1 rounded-md w-full text-center\"");
							var __jte_html_attribute_9 = set.getWeight();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_9)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_9);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n                                        </td>\n                                        <td class=\"p-2 text-center\">\n                                            <input type=\"checkbox\" name=\"warmup-");
							jteOutput.setContext("input", "name");
							jteOutput.writeUserContent(i);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("[]\"");
							var __jte_html_attribute_10 = set.isWarmup();
							if (__jte_html_attribute_10) {
							jteOutput.writeContent(" checked");
							}
							jteOutput.writeContent(">\n                                        </td>\n                                        <td class=\"p-2 text-center\">\n                                            <button type=\"button\" onclick=\"removeSet(this)\" class=\"text-red-500 font-bold\">\n                                                <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n                                            </button>\n                                        </td>\n                                    </tr>\n                                    ");
						}
						jteOutput.writeContent("\n                                    </tbody>\n                                </table>\n                            </div>\n                            <button type=\"button\" onclick=\"addSet(");
						jteOutput.setContext("button", "onclick");
						jteOutput.writeUserContent(i);
						jteOutput.setContext("button", null);
						jteOutput.writeContent(")\" class=\"bg-blue-500 px-4 py-2 rounded-md\">\n                                + Add Set\n                            </button>\n                        </div>\n                ");
					}
					jteOutput.writeContent("\n\n            ");
				}
				jteOutput.writeContent("\n                <div id=\"exerciseList\"></div>\n        </div>\n\n        <button type=\"button\" onclick=\"addExercise()\">+ Add Exercise</button>\n\n        <button type=\"submit\" class=\"bg-green-500 text-white px-3 py-2 rounded-md mt-4 w-full\">\n            ");
				jteOutput.setContext("button", null);
				jteOutput.writeUserContent(template == null ? "Create Template" : "Edit Template");
				jteOutput.writeContent("\n        </button>\n    </form>\n</div>\n<script src=\"../scripts/add-exercise.js\"></script>\n<script src=\"https://unpkg.com/htmx-ext-json-enc@2.0.1/json-enc.js\"></script>\n\n\n");
			}
		}, template != null ? "Edit template" : "Create a template");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao template = (progressive_overlords.entities.dao.WorkoutDao)params.get("template");
		render(jteOutput, jteHtmlInterceptor, template);
	}
}
