package gg.jte.generated.ondemand.pages.records;
public final class JterecordspageGenerated {
	public static final String JTE_NAME = "pages/records/records-page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,2,2,19,19,24,24,24,24,24,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "records");
				jteOutput.writeContent("\n<div id=\"workoutForm\">\n    <div class=\"flex-row align-center justify-between\">\n        <h1>Records</h1>\n        <div class=\"flex-row align-center gap-1\">\n            <a href=\"/history\">\n                <button class=\"button main-button gap-05\">\n                    View workout history\n                </button>\n            </a>\n        </div>\n    </div>\n    <div class=\"flex-row gap-2\">\n        <div class=\"w-full\" id=\"exercise-data-container\">\n            <h3>Select an exercise to check your statistics</h3>\n        </div>\n        <div style=\"min-width: 18rem\">\n            ");
				gg.jte.generated.ondemand.components.exercises.JteexercisesidebarGenerated.render(jteOutput, jteHtmlInterceptor, exercises);
				jteOutput.writeContent("\n        </div>\n    </div>\n</div>\n<script src=\"https://cdn.jsdelivr.net/npm/echarts@5.6.0/dist/echarts.min.js\"></script>\n");
			}
		}, "Routines");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.ExerciseDao> exercises = (java.util.List<progressive_overlords.entities.dao.ExerciseDao>)params.get("exercises");
		render(jteOutput, jteHtmlInterceptor, exercises);
	}
}
