package gg.jte.generated.ondemand.pages;
public final class JtehomeGenerated {
	public static final String JTE_NAME = "pages/home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,3,3,9,9,10,10,13,13,13,15,15,15,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.PublicUserDao> userSuggestionList, progressive_overlords.entities.dao.PublicUserDao ownUserDetails) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "feed");
				jteOutput.writeContent("\n    <div class=\"flex-row w-full gap-2\">\n        <div style=\"width: 80%\">\n            <p>Home page</p>\n        </div>\n        <div class=\"flex-column gap-1 mobile-hidden\">\n            ");
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
		render(jteOutput, jteHtmlInterceptor, userSuggestionList, ownUserDetails);
	}
}
