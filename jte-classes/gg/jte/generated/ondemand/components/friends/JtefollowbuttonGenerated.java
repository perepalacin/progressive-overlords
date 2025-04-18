package gg.jte.generated.ondemand.components.friends;
public final class JtefollowbuttonGenerated {
	public static final String JTE_NAME = "components/friends/follow-button.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.UUID userId) {
		jteOutput.writeContent("<button class=\"button muted-button w-6\" hx-post=\"/api/v1/friends/");
		jteOutput.setContext("button", "hx-post");
		jteOutput.writeUserContent(String.valueOf(userId));
		jteOutput.setContext("button", null);
		jteOutput.writeContent("\" hx-target=\"this\" hx-swap=\"outerHTML\">Follow</button>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.UUID userId = (java.util.UUID)params.get("userId");
		render(jteOutput, jteHtmlInterceptor, userId);
	}
}
