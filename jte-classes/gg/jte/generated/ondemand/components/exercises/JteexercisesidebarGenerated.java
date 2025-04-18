package gg.jte.generated.ondemand.components.exercises;
public final class JteexercisesidebarGenerated {
	public static final String JTE_NAME = "components/exercises/exercise-sidebar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,13,13,13,14,14,15,15,21,21,21,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises) {
		jteOutput.writeContent("<input type=\"search\"\n    name=\"query\"\n    required\n    autocomplete=\"off\"\n    hx-trigger=\"input changed delay:500ms, keyup[key=='Enter'], click\"\n    hx-get=\"/api/v1/exercises?page=0&data=true\"\n    placeholder=\"Search exercises\"\n    hx-target=\"#exercises-sidebar\"\n    hx-swap=\"innerHTML\"\n    class=\"w-full\"\n>\n<div id=\"exercises-sidebar\">\n    ");
		for (progressive_overlords.entities.dao.ExerciseDao exercise : exercises) {
			jteOutput.writeContent("\n        ");
			gg.jte.generated.ondemand.components.exercises.JteexerciseitemGenerated.render(jteOutput, jteHtmlInterceptor, exercise);
			jteOutput.writeContent("\n    ");
		}
		jteOutput.writeContent("\n    <button hx-get=\"/api/v1/exercises?data=true&page=1\" hx-target=\"this\" hx-trigger=\"click\" hx-swap=\"outerHTML\" type=\"button\" class=\"button ghost-button w-full\">\n        Load more\n    </button>\n</div>\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises = (java.util.List<progressive_overlords.entities.dao.ExerciseDao>)params.get("exercises");
		render(jteOutput, jteHtmlInterceptor, exercises);
	}
}
