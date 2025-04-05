package gg.jte.generated.ondemand.pages.workouts;
public final class JteongoingworkoutviewGenerated {
	public static final String JTE_NAME = "pages/workouts/ongoing-workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,3,3,4,4,4,4,4,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.workouts.JteongoingworkoutviewGenerated.render(jteOutput, jteHtmlInterceptor, workout);
				jteOutput.writeContent("\n");
			}
		}, workout.getName());
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		render(jteOutput, jteHtmlInterceptor, workout);
	}
}
