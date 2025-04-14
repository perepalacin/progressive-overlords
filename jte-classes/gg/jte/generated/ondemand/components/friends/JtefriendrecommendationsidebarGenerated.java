package gg.jte.generated.ondemand.components.friends;
public final class JtefriendrecommendationsidebarGenerated {
	public static final String JTE_NAME = "components/friends/friend-recommendation-sidebar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,6,6,9,9,10,10,10,12,12,14,14,17,17,18,18,18,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList) {
		jteOutput.writeContent("\n");
		if (!userSuggestionList.isEmpty()) {
			jteOutput.writeContent("\n    <aside class=\"user-suggestion-sidebar\">\n        <p class=\"font-bold mb-1\">Suggested users</p>\n        <ul class=\"flex-column gap-1\">\n           ");
			for (progressive_overlords.entities.dao.PublicUserDao user : userSuggestionList) {
				jteOutput.writeContent("\n                <li class=\"flex-row w-full align-center justify-between gap-1\">\n                    <div class=\"flex-row gap-2 align-center\">\n                        ");
				gg.jte.generated.ondemand.components.friends.JteuserlogoGenerated.render(jteOutput, jteHtmlInterceptor, user.getUsername());
				jteOutput.writeContent("\n                        <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(user.getUsername());
				jteOutput.writeContent("</p>\n                    </div>\n                    ");
				gg.jte.generated.ondemand.components.friends.JtefollowbuttonGenerated.render(jteOutput, jteHtmlInterceptor, user.getUserId());
				jteOutput.writeContent("\n                </li>\n           ");
			}
			jteOutput.writeContent("\n        </ul>\n    </aside>\n");
		}
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList = (java.util.List<progressive_overlords.entities.dao.PublicUserDao>)params.get("userSuggestionList");
		render(jteOutput, jteHtmlInterceptor, userSuggestionList);
	}
}
