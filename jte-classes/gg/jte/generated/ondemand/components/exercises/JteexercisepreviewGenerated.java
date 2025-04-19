package gg.jte.generated.ondemand.components.exercises;
public final class JteexercisepreviewGenerated {
	public static final String JTE_NAME = "components/exercises/exercise-preview.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,3,3,3,3,3,3,3,3,4,4,4,5,5,5,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.ExerciseDao exercise) {
		jteOutput.writeContent("\n<div class=\"exercise-preview\">\n    <img loading=\"lazy\"");
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
		jteOutput.writeContent("</p>\n</div>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.ExerciseDao exercise = (progressive_overlords.entities.dao.ExerciseDao)params.get("exercise");
		render(jteOutput, jteHtmlInterceptor, exercise);
	}
}
