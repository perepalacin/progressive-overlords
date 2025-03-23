package gg.jte.generated.ondemand.responses.exercises;
public final class JteexercisesdropdownlistGenerated {
	public static final String JTE_NAME = "responses/exercises/exercises-dropdown-list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,3,5,5,6,6,8,8,8,8,8,8,8,8,8,9,9,9,11,11,12,12,13,13,13,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises) {
		jteOutput.writeContent("\n<p>Exercise Drop down:</p>\n");
		if (exercises == null) {
			jteOutput.writeContent("\n    <p>Empty list!</p>\n");
		} else {
			jteOutput.writeContent("\n    ");
			for (progressive_overlords.entities.dao.ExerciseDao exercise : exercises) {
				jteOutput.writeContent("\n        <li class=\"flex flex-row gap-2\">\n            <img");
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
			jteOutput.writeContent("\n");
		}
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises = (java.util.List<progressive_overlords.entities.dao.ExerciseDao>)params.get("exercises");
		render(jteOutput, jteHtmlInterceptor, exercises);
	}
}
