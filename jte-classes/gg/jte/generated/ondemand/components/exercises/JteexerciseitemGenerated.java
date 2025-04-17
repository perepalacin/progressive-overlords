package gg.jte.generated.ondemand.components.exercises;
public final class JteexerciseitemGenerated {
	public static final String JTE_NAME = "components/exercises/exercise-item.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,1,1,2,2,2,2,2,2,2,2,2,3,3,3,6,6,6,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.ExerciseDao exercise) {
		jteOutput.writeContent("<li class=\"exercise-dropdown-item\" hx-trigger=\"click\" hx-get=\"/api/v1/exercises/data/");
		jteOutput.setContext("li", "hx-get");
		jteOutput.writeUserContent(exercise.getId());
		jteOutput.setContext("li", null);
		jteOutput.writeContent("\" hx-target=\"#exercise-data-container\" hx-swap=\"innerHTML\">\n    <img loading=\"lazy\"");
		var __jte_html_attribute_0 = exercise.getThumbnail();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" src=\"");
			jteOutput.setContext("img", "src");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("img", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\n    <p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(exercise.getName());
		jteOutput.writeContent("</p>\n</li>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.ExerciseDao exercise = (progressive_overlords.entities.dao.ExerciseDao)params.get("exercise");
		render(jteOutput, jteHtmlInterceptor, exercise);
	}
}
