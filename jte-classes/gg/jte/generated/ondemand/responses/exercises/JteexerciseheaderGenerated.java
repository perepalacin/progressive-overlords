package gg.jte.generated.ondemand.responses.exercises;
public final class JteexerciseheaderGenerated {
	public static final String JTE_NAME = "responses/exercises/exercise-header.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,2,2,2,2,2,2,2,2,2,3,3,5,5,5,5,12,12,12,12,12,12,12,12,12,14,14,14,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.ExerciseDao exercise) {
		String randomId = "exercise-" + String.valueOf(new java.util.Random().nextInt(900000) + 100000);
		jteOutput.writeContent("\n<div class=\"flex-row align-center justify-between mb-1 px-1 exercise-header\"");
		var __jte_html_attribute_0 = randomId;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" id=\"");
			jteOutput.setContext("div", "id");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n    ");
		gg.jte.generated.ondemand.components.exercises.JteexercisepreviewGenerated.render(jteOutput, jteHtmlInterceptor, exercise);
		jteOutput.writeContent("\n    <div class=\"flex-row gap-025\">\n        <button type=\"button\" class=\"ghost-button-icon\" hx-get=\"/api/v1/exercises/selector\" hx-trigger=\"click\" hx-target=\"#");
		jteOutput.setContext("button", "hx-target");
		jteOutput.writeUserContent(randomId);
		jteOutput.setContext("button", null);
		jteOutput.writeContent("\" hx-swap=\"outerHTML\">\n            <img src=\"../icons/repeat-2.svg\" style=\"width: 1rem\" />\n        </button>\n        <button type=\"button\" class=\"ghost-button-icon remove-exercise-btn\">\n            <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n        </button>\n    </div>\n    <input class=\"hidden\"");
		var __jte_html_attribute_1 = exercise.getId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"exerciseIds[]\" required/>\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.ExerciseDao exercise = (progressive_overlords.entities.dao.ExerciseDao)params.get("exercise");
		render(jteOutput, jteHtmlInterceptor, exercise);
	}
}
