package gg.jte.generated.ondemand.responses.exercises;
public final class JteexercisesdropdownlistGenerated {
	public static final String JTE_NAME = "responses/exercises/exercises-dropdown-list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,5,5,7,7,8,8,9,9,11,11,11,11,11,11,11,11,11,12,12,12,14,14,15,15,16,16,16,16,16,16,16,16,19,19,20,20,21,21,21,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises, int page, String query) {
		jteOutput.writeContent("\n");
		if (exercises == null || exercises.isEmpty()) {
			jteOutput.writeContent("\n    ");
			if (page == 0 ) {
				jteOutput.writeContent("\n        <p>No results found...</p>\n    ");
			}
			jteOutput.writeContent("\n");
		} else {
			jteOutput.writeContent("\n    ");
			for (progressive_overlords.entities.dao.ExerciseDao exercise : exercises) {
				jteOutput.writeContent("\n        <li class=\"dropdown-item flex flex-row items-center justify-start gap-4 px-4 py-2 cursor-pointer hover:bg-gray-200\">\n            <img class=\"w-6 h-6 rounded-full\"");
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
				jteOutput.writeContent("\n        <button hx-trigger=\"click\" hx-get=\"/api/v1/exercises?page=");
				jteOutput.setContext("button", "hx-get");
				jteOutput.writeUserContent(page+1);
				jteOutput.setContext("button", null);
				jteOutput.writeContent("&query=");
				jteOutput.setContext("button", "hx-get");
				jteOutput.writeUserContent(query);
				jteOutput.setContext("button", null);
				jteOutput.writeContent("\" hx-target=\"this\" hx-swap=\"outerHTML\">\n            Load more\n        </button>\n    ");
			}
			jteOutput.writeContent("\n");
		}
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises = (java.util.List<progressive_overlords.entities.dao.ExerciseDao>)params.get("exercises");
		int page = (int)params.get("page");
		String query = (String)params.get("query");
		render(jteOutput, jteHtmlInterceptor, exercises, page, query);
	}
}
