package gg.jte.generated.ondemand.responses.exercises;
public final class JteexercisesselectorGenerated {
	public static final String JTE_NAME = "responses/exercises/exercises-selector.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,1,1,1,9,9,9,9,9,9};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<div class=\"flex-row align-center justify-between mb-1 px-2 exercise-header\">\n    ");
		gg.jte.generated.ondemand.components.exercises.JteexercisesdropdownGenerated.render(jteOutput, jteHtmlInterceptor);
		jteOutput.writeContent("\n    <div class=\"flex-row gap-025\">\n        <button type=\"button\" class=\"ghost-button-icon remove-exercise-btn\">\n            <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n        </button>\n    </div>\n</div>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
