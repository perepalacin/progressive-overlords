package gg.jte.generated.ondemand.pages.workouts;
public final class JteworkoutviewGenerated {
	public static final String JTE_NAME = "pages/workouts/workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,3,3,3,4,4,6,6,8,8,8,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workoutDao) {
		jteOutput.writeContent("\n");
		if (workoutDao != null) {
			jteOutput.writeContent("\n<p>");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(workoutDao.getName());
			jteOutput.writeContent("</p>\n");
		} else {
			jteOutput.writeContent("\n <p>Give a name to your workout!</p>\n");
		}
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workoutDao = (progressive_overlords.entities.dao.WorkoutDao)params.get("workoutDao");
		render(jteOutput, jteHtmlInterceptor, workoutDao);
	}
}
