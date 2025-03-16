package gg.jte.generated.ondemand.pages.workouts;
public final class JteworkoutviewGenerated {
	public static final String JTE_NAME = "pages/workouts/workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,3,3,3,4,4,4,5,5,7,7,9,9,9,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout) {
		jteOutput.writeContent("\n");
		if (workout != null) {
			jteOutput.writeContent("\n<p>");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(workout.getName());
			jteOutput.writeContent("</p>\n <p>It has a total of ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(workout.getExercises().size());
			jteOutput.writeContent(" exercises</p>\n");
		} else {
			jteOutput.writeContent("\n <p>Give a name to your workout!</p>\n");
		}
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		render(jteOutput, jteHtmlInterceptor, workout);
	}
}
