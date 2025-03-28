package gg.jte.generated.ondemand.responses.exercises;
public final class JteexercisesdropdownGenerated {
	public static final String JTE_NAME = "responses/exercises/exercises-dropdown.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,6,6,8,8,9,9,10,10,11,11,11,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises, int page, String query) {
		jteOutput.writeContent("\n");
		if (page == 0) {
			jteOutput.writeContent("\n    <div class=\"exercises-drop-down-response\">\n        ");
			gg.jte.generated.ondemand.responses.exercises.JteexercisesdropdownlistGenerated.render(jteOutput, jteHtmlInterceptor, exercises, page, query);
			jteOutput.writeContent("\n    </div>\n");
		} else {
			jteOutput.writeContent("\n    ");
			gg.jte.generated.ondemand.responses.exercises.JteexercisesdropdownlistGenerated.render(jteOutput, jteHtmlInterceptor, exercises, page, query);
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
