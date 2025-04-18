package gg.jte.generated.ondemand.pages.workouts;
public final class JteworkouthistoryGenerated {
	public static final String JTE_NAME = "pages/workouts/workout-history.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,2,2,7,7,11,11,11,13,13,13,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> workoutHistory) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "records");
				jteOutput.writeContent("\n    <div class=\"flex-row w-full justify-between\">\n        <div class=\"px-2 py-1 flex-column w-full align-center\">\n            <h1 style=\"align-self: start\">Workout History</h1>\n            <div class=\"px-2 py-1 flex-column w-full\" style=\"max-width: 800px\">\n                ");
				gg.jte.generated.ondemand.components.feeds.JtefeedactivityGenerated.render(jteOutput, jteHtmlInterceptor, workoutHistory, true, 1);
				jteOutput.writeContent("\n            </div>\n        </div>\n    </div>\n");
			}
		}, "Home");
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> workoutHistory = (java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao>)params.get("workoutHistory");
		render(jteOutput, jteHtmlInterceptor, workoutHistory);
	}
}
