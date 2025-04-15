package gg.jte.generated.ondemand.components.feeds;
public final class JtefeedactivityGenerated {
	public static final String JTE_NAME = "components/feeds/feed-activity.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,5,5,7,7,7,8,8,8,11,11,11,15,15,15,19,19,19,23,23,24,24,24,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity) {
		jteOutput.writeContent("<ul class=\"flex-column gap-1\">\n    ");
		for (progressive_overlords.entities.dao.WorkoutSummaryDao summary : feedActivity) {
			jteOutput.writeContent("\n        <li>\n            <div class=\"flex-row gap-2 align-center\">\n                ");
			gg.jte.generated.ondemand.components.friends.JteuserlogoGenerated.render(jteOutput, jteHtmlInterceptor, summary.getPublicUserDao().getUsername());
			jteOutput.writeContent("\n                <div>\n                    <p>");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(summary.getPublicUserDao().getUsername());
			jteOutput.writeContent("</p>\n                    <span class=\"label\">");
			jteOutput.setContext("span", null);
			jteOutput.writeUserContent(summary.getStartDate());
			jteOutput.writeContent("</span>\n                </div>\n            </div>\n            <p>");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(summary.getName());
			jteOutput.writeContent("</p>\n            <div class=\"flex-row gap-2\">\n                <div>\n                    <span class=\"label\">Volume:</span>\n                    <p class=\"font-bold\">");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(summary.getVolume());
			jteOutput.writeContent(" kg</p>\n                </div>\n                <div>\n                    <span class=\"label\">Duration:</span>\n                    <p class=\"font-bold\">");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(progressive_overlords.utils.TimeDiffHelper.getTimeDifference(summary.getDuration()));
			jteOutput.writeContent("</p>\n                </div>\n            </div>\n        </li>\n    ");
		}
		jteOutput.writeContent("\n</ul>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity = (java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao>)params.get("feedActivity");
		render(jteOutput, jteHtmlInterceptor, feedActivity);
	}
}
