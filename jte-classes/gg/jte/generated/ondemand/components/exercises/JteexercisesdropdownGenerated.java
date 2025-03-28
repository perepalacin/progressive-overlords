package gg.jte.generated.ondemand.components.exercises;
public final class JteexercisesdropdownGenerated {
	public static final String JTE_NAME = "components/exercises/exercises-dropdown.jte";
	public static final int[] JTE_LINE_INFO = {10,10,10,10,10,10,10,10,10,10,10};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<div class=\"exercises-drop-down\">\n    <input type=\"search\"\n           name=\"query\"\n           autocomplete=\"off\"\n           hx-trigger=\"input changed delay:500ms, keyup[key=='Enter'], click\"\n           hx-get=\"/api/v1/exercises?page=0\"\n           placeholder=\"Select an exercise\"\n           hx-target=\"closest .exercises-drop-down\"\n           hx-swap=\"beforeend\"\n    >\n</div>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
