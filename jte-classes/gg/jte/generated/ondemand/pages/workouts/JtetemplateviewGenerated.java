package gg.jte.generated.ondemand.pages.workouts;
public final class JtetemplateviewGenerated {
	public static final String JTE_NAME = "pages/workouts/template-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,6,6,6,6,7,7,7,11,14,14,14,14,19,19,19,19,28,28,28,31,31,31,35,35,36,36,41,41,41,53,53,55,55,55,57,57,57,60,60,60,63,63,63,63,63,66,66,71,71,73,73,76,76,76,76,76,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao template) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.pages.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div id=\"template-page\" class=\"flex flex-col gap-2 w-full md:w-[600px]\">\n        <div class=\"flex flex-row gap-1 justify-between items-center\">\n            <div class=\"flex flex-row gap-2 items-center\">\n                <div class=\"min-w-[1rem] min-h-[1rem] rounded-md\" style=\"background-color: ");
				jteOutput.setContext("div", "style");
				jteOutput.writeUserContent(template.getColor());
				jteOutput.setContext("div", null);
				jteOutput.writeContent("\"></div>\n                <h1 class=\"text-xl font-semibold\">");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(template.getName());
				jteOutput.writeContent("</h1>\n            </div>\n            <div class=\"flex flex-row gap-2 items-center\">\n                <button class=\"flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100\">\n                    ");
				jteOutput.writeContent("\n                    Start\n                </button>\n                <a href=\"/edit-template/");
				jteOutput.setContext("a", "href");
				jteOutput.writeUserContent(template.getId());
				jteOutput.setContext("a", null);
				jteOutput.writeContent("\">\n                    <button class=\"flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100\">\n                        <img src=\"../icons/pen.svg\" style=\"width: 1rem\"/> Edit\n                    </button>\n                </a>\n                <button hx-swap=\"innerHTML\" hx-target=\"#template-page\" hx-delete=\"/api/v1/workouts/");
				jteOutput.setContext("button", "hx-delete");
				jteOutput.writeUserContent(template.getId());
				jteOutput.setContext("button", null);
				jteOutput.writeContent("\" class=\"flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100\">\n                    <img src=\"../icons/trash.svg\" style=\"width: 1rem\"/> Delete\n                </button>\n            </div>\n        </div>\n            <p>\n                <span>\n                    Description:\n                </span>\n                ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(template.getDescription());
				jteOutput.writeContent("\n            </p>\n\n            <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(template.getUnparsedTags());
				jteOutput.writeContent("</p>\n\n            <div id=\"exerciseContainer\">\n                <h3 class=\"font-bold mt-4\">Exercises</h3>\n                ");
				if (template != null && template.getExercises() != null) {
					jteOutput.writeContent("\n                    ");
					for (int i = 0; i < template.getExercises().size(); i++) {
						jteOutput.writeContent("\n                        <div class=\"exercise-item border px-4 py-2 mb-2 rounded-md\">\n                            <div class=\"flex flex-row align-center justify-between py-2\">\n                                <label>Exercise:</label>\n                            </div>\n                            <h4>");
						jteOutput.setContext("h4", null);
						jteOutput.writeUserContent(template.getExercises().get(i).getExerciseId());
						jteOutput.writeContent("</h4>\n                            <div class=\"sets-container border-collapse border rounded-md\">\n                                <table class=\"w-full \">\n                                    <thead class=\"bg-gray-100\">\n                                    <tr>\n                                        <th class=\"p-2\">Sets</th>\n                                        <th class=\"p-2\">Reps</th>\n                                        <th class=\"p-2\">Weight</th>\n                                        <th class=\"p-2\">Warmup</th>\n                                    </tr>\n                                    </thead>\n                                    <tbody>\n                                    ");
						for (progressive_overlords.entities.dao.SetDao set : template.getExercises().get(i).getSets()) {
							jteOutput.writeContent("\n                                        <tr>\n                                            <td class=\"p-2 text-center\">");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(set.getSetNum() +1);
							jteOutput.writeContent("</td>\n                                            <td class=\"p-2\">\n                                                <p class=\"px-2 py-1 w-full text-center\">");
							jteOutput.setContext("p", null);
							jteOutput.writeUserContent(set.getReps());
							jteOutput.writeContent("</p>\n                                            </td>\n                                            <td class=\"p-2\">\n                                                <p class=\"px-2 py-1 w-full text-center\">");
							jteOutput.setContext("p", null);
							jteOutput.writeUserContent(set.getWeight());
							jteOutput.writeContent("</p>\n                                            </td>\n                                            <td class=\"p-2 text-center\">\n                                                <input type=\"checkbox\" disabled=\"true\"");
							var __jte_html_attribute_0 = set.isWarmup();
							if (__jte_html_attribute_0) {
							jteOutput.writeContent(" checked");
							}
							jteOutput.writeContent(">\n                                            </td>\n                                        </tr>\n                                    ");
						}
						jteOutput.writeContent("\n                                    </tbody>\n                                </table>\n                            </div>\n                        </div>\n                    ");
					}
					jteOutput.writeContent("\n\n                ");
				}
				jteOutput.writeContent("\n            </div>\n    </div>\n");
			}
		}, "Template: " + template.getName());
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao template = (progressive_overlords.entities.dao.WorkoutDao)params.get("template");
		render(jteOutput, jteHtmlInterceptor, template);
	}
}
