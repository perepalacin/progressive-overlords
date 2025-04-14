package gg.jte.generated.ondemand.components.friends;
public final class JteuserlogoGenerated {
	public static final String JTE_NAME = "components/friends/user-logo.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,1,1,1,1,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String username) {
		jteOutput.writeContent("<p class=\"user-logo\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(username.toUpperCase().charAt(0));
		jteOutput.writeContent("</p>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String username = (String)params.get("username");
		render(jteOutput, jteHtmlInterceptor, username);
	}
}
