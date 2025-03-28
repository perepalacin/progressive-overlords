package gg.jte.generated.ondemand.responses.exercises;
public final class JteexercisesdropdownlistGenerated {
	public static final String JTE_NAME = "responses/exercises/exercises-dropdown-list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,5,5,9,9,10,10,11,11,12,12,12,12,13,13,13,13,13,13,13,13,13,14,14,14,16,16,17,17,19,19,19,19,19,19,19,19,23,23,24,24,24,24,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises, int page, String query) {
		jteOutput.writeContent("\n");
		if (exercises == null || exercises.isEmpty()) {
			jteOutput.writeContent("\n    ");
			if (page == 0 ) {
				jteOutput.writeContent("\n        <li class=\"exercise-dropdown-item\">\n            <p>No results found...</p>\n        </li>\n    ");
			}
			jteOutput.writeContent("\n");
		} else {
			jteOutput.writeContent("\n    ");
			for (progressive_overlords.entities.dao.ExerciseDao exercise : exercises) {
				jteOutput.writeContent("\n        <li class=\"exercise-dropdown-item\" hx-trigger=\"click\" hx-get=\"/api/v1/exercises/preview/");
				jteOutput.setContext("li", "hx-get");
				jteOutput.writeUserContent(exercise.getId());
				jteOutput.setContext("li", null);
				jteOutput.writeContent("\" hx-target=\"closest .exercise-header\" hx-swap=\"outerHTML\">\n            <img");
				var __jte_html_attribute_0 = exercise.getThumbnail();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" src=\"");
					jteOutput.setContext("img", "src");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("img", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\n            <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(exercise.getName());
				jteOutput.writeContent("</p>\n        </li>\n    ");
			}
			jteOutput.writeContent("\n    ");
			if (exercises.size() == 20) {
				jteOutput.writeContent("\n        <div class=\"w-full\">\n            <button hx-trigger=\"click\" hx-get=\"/api/v1/exercises?page=");
				jteOutput.setContext("button", "hx-get");
				jteOutput.writeUserContent(page+1);
				jteOutput.setContext("button", null);
				jteOutput.writeContent("&query=");
				jteOutput.setContext("button", "hx-get");
				jteOutput.writeUserContent(query);
				jteOutput.setContext("button", null);
				jteOutput.writeContent("\" hx-target=\"closest div\" hx-swap=\"outerHTML\" type=\"button\" class=\"button ghost-button w-full\">\n                Load more\n            </button>\n        </div>\n    ");
			}
			jteOutput.writeContent("\n");
		}
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises = (java.util.List<progressive_overlords.entities.dao.ExerciseDao>)params.get("exercises");
		int page = (int)params.get("page");
		String query = (String)params.get("query");
		render(jteOutput, jteHtmlInterceptor, exercises, page, query);
	}
}
