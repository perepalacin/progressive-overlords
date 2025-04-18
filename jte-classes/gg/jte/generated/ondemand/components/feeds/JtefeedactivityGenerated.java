package gg.jte.generated.ondemand.components.feeds;
public final class JtefeedactivityGenerated {
	public static final String JTE_NAME = "components/feeds/feed-activity.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,4,4,4,5,5,5,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity, boolean isOwnWorkout, int newPage) {
		jteOutput.writeContent("<ul class=\"flex-column gap-1 w-full\">\n    ");
		gg.jte.generated.ondemand.components.feeds.JtefeedactivityitemsGenerated.render(jteOutput, jteHtmlInterceptor, feedActivity, isOwnWorkout, newPage);
		jteOutput.writeContent("\n</ul>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity = (java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao>)params.get("feedActivity");
		boolean isOwnWorkout = (boolean)params.get("isOwnWorkout");
		int newPage = (int)params.get("newPage");
		render(jteOutput, jteHtmlInterceptor, feedActivity, isOwnWorkout, newPage);
	}
}
