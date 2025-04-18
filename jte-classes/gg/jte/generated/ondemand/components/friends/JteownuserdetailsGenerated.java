package gg.jte.generated.ondemand.components.friends;
public final class JteownuserdetailsGenerated {
	public static final String JTE_NAME = "components/friends/own-user-details.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,3,4,4,4,8,8,8,12,12,12,16,16,16,20,20,20,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.PublicUserDao userDetails) {
		jteOutput.writeContent("\n<aside class=\"user-details-sidebar\">\n    ");
		gg.jte.generated.ondemand.components.friends.JteuserlogoGenerated.render(jteOutput, jteHtmlInterceptor, userDetails.getUsername());
		jteOutput.writeContent("\n    <p class=\"font-bold mb-1 text-center\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(userDetails.getUsername());
		jteOutput.writeContent("</p>\n    <ul class=\"flex-row gap-1\">\n        <li class=\"flex-column align-center gap-05\">\n            <p class=\"label\">Workouts</p>\n            <p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(userDetails.getWorkouts());
		jteOutput.writeContent("</p>\n        </li>\n        <li class=\"flex-column align-center gap-05\">\n            <p class=\"label\">Following</p>\n            <p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(userDetails.getFollowing());
		jteOutput.writeContent("</p>\n        </li>\n        <li class=\"flex-column align-center gap-05 \">\n            <p class=\"label\">Followers</p>\n            <p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(userDetails.getFollowers());
		jteOutput.writeContent("</p>\n        </li>\n    </ul>\n</aside>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.PublicUserDao userDetails = (progressive_overlords.entities.dao.PublicUserDao)params.get("userDetails");
		render(jteOutput, jteHtmlInterceptor, userDetails);
	}
}
