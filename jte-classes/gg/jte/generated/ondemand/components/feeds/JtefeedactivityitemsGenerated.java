package gg.jte.generated.ondemand.components.feeds;
public final class JtefeedactivityitemsGenerated {
	public static final String JTE_NAME = "components/feeds/feed-activity-items.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,5,5,5,5,6,6,8,8,10,10,10,11,11,11,14,14,15,15,15,16,16,17,17,17,18,18,22,22,22,26,26,26,29,29,31,31,31,31,31,31,31,31,31,32,32,32,32,32,32,34,34,37,37,38,38,39,39,39,39,40,40,40,40,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity, boolean isOwnWorkout, int newPage) {
		for (progressive_overlords.entities.dao.WorkoutSummaryDao summary : feedActivity) {
			jteOutput.writeContent("\n    <li>\n        <a class=\"feed-item\" href=\"/workout/");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(summary.getWorkoutId());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\">\n            ");
			if (!isOwnWorkout) {
				jteOutput.writeContent("\n                <div class=\"flex-row gap-2 align-center\">\n                    ");
				gg.jte.generated.ondemand.components.friends.JteuserlogoGenerated.render(jteOutput, jteHtmlInterceptor, summary.getPublicUserDao().getUsername());
				jteOutput.writeContent("\n                    <div>\n                        <h3 class=\"m-0 font-normal\">");
				jteOutput.setContext("h3", null);
				jteOutput.writeUserContent(summary.getPublicUserDao().getUsername());
				jteOutput.writeContent("</h3>\n                        <span class=\"label\">");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(progressive_overlords.utils.DateFormatter.formatToFriendlyDate(summary.getStartDate()));
				jteOutput.writeContent("</span>\n                    </div>\n                </div>\n            ");
			}
			jteOutput.writeContent("\n            <h2 class=\"my-025\">");
			jteOutput.setContext("h2", null);
			jteOutput.writeUserContent(summary.getName());
			jteOutput.writeContent("</h2>\n            ");
			if (isOwnWorkout) {
				jteOutput.writeContent("\n                <span class=\"label\">");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(progressive_overlords.utils.DateFormatter.formatToFriendlyDate(summary.getStartDate()));
				jteOutput.writeContent("</span>\n            ");
			}
			jteOutput.writeContent("\n            <div class=\"flex-row gap-2\">\n                <div>\n                    <span class=\"label\">Volume:</span>\n                    <p class=\"font-bold\">");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(summary.getVolume());
			jteOutput.writeContent(" kg</p>\n                </div>\n                <div>\n                    <span class=\"label\">Duration:</span>\n                    <p class=\"font-bold\">");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(progressive_overlords.utils.TimeDiffHelper.getTimeDifference(summary.getDuration()));
			jteOutput.writeContent("</p>\n                </div>\n            </div>\n            ");
			for (progressive_overlords.entities.dao.WorkoutExerciseDao exercise : summary.getWorkoutExercises()) {
				jteOutput.writeContent("\n                <div class=\"exercise-dropdown-item\">\n                    <img loading=\"lazy\"");
				var __jte_html_attribute_0 = exercise.getExercise().getThumbnail();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" src=\"");
					jteOutput.setContext("img", "src");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("img", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" />\n                    <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(exercise.getSetsCount());
				jteOutput.writeContent(" sets - ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(exercise.getExercise().getName());
				jteOutput.writeContent("</p>\n                </div>\n            ");
			}
			jteOutput.writeContent("\n        </a>\n    </li>\n");
		}
		jteOutput.writeContent("\n");
		if (feedActivity.size() == 4) {
			jteOutput.writeContent("\n    <div style=\"height: 1rem;\" hx-get=\"/api/v1/history?page=");
			jteOutput.setContext("div", "hx-get");
			jteOutput.writeUserContent(newPage);
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\" hx-target=\"this\" hx-trigger=\"revealed\" hx-swap=\"outerHTML\"></div>\n");
		}
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity = (java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao>)params.get("feedActivity");
		boolean isOwnWorkout = (boolean)params.get("isOwnWorkout");
		int newPage = (int)params.get("newPage");
		render(jteOutput, jteHtmlInterceptor, feedActivity, isOwnWorkout, newPage);
	}
}
