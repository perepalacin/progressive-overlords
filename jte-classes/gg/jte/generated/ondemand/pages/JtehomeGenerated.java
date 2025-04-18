package gg.jte.generated.ondemand.pages;
public final class JtehomeGenerated {
	public static final String JTE_NAME = "pages/home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,3,4,4,9,9,13,13,14,14,17,17,17,19,19,19,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList, progressive_overlords.entities.dao.PublicUserDao ownUserDetails, java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "feed");
				jteOutput.writeContent("\n    <div class=\"flex-row w-full justify-between\">\n        <div class=\"px-2 py-1 flex-column w-full align-center\">\n            <h1 style=\"align-self: start\">Home</h1>\n            <div class=\"px-2 py-1 flex-column w-full\" style=\"max-width: 800px\">\n                ");
				gg.jte.generated.ondemand.components.feeds.JtefeedactivityGenerated.render(jteOutput, jteHtmlInterceptor, feedActivity, false, 1);
				jteOutput.writeContent("\n            </div>\n        </div>\n        <div class=\"flex-column gap-1 mobile-hidden px-1 py-1\">\n            ");
				gg.jte.generated.ondemand.components.friends.JteownuserdetailsGenerated.render(jteOutput, jteHtmlInterceptor, ownUserDetails);
				jteOutput.writeContent("\n            ");
				gg.jte.generated.ondemand.components.friends.JtefriendrecommendationsidebarGenerated.render(jteOutput, jteHtmlInterceptor, userSuggestionList);
				jteOutput.writeContent("\n        </div>\n    </div>\n");
			}
		}, "Home");
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList = (java.util.List<progressive_overlords.entities.dao.PublicUserDao>)params.get("userSuggestionList");
		progressive_overlords.entities.dao.PublicUserDao ownUserDetails = (progressive_overlords.entities.dao.PublicUserDao)params.get("ownUserDetails");
		java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao> feedActivity = (java.util.List<progressive_overlords.entities.dao.WorkoutSummaryDao>)params.get("feedActivity");
		render(jteOutput, jteHtmlInterceptor, userSuggestionList, ownUserDetails, feedActivity);
	}
}
