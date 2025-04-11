package gg.jte.generated.ondemand;
public final class JtemainGenerated {
	public static final String JTE_NAME = "main.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,7,7,7,7,16,16,16,21,21,21,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, gg.jte.Content content, String tabName) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\" name=\"htmx-config\" content='{\"responseHandling\": [{\"code\":\".*\", \"swap\": true}]}'>\n    <link rel=\"icon\" href=\"https://www.notion.so/icons/gym_red.svg?mode=light\">\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(tabName);
		jteOutput.writeContent(" - Progressive Overlords</title>\n    <script src=\"https://unpkg.com/htmx.org@2.0.3\"\n            integrity=\"sha384-0895/pl2MU10Hqc6jd4RvrthNlDiE9U1tWmX7WRESftEDRosgxNsQG/Ze9YMRzHq\"\n            crossorigin=\"anonymous\"></script>\n    <script src=\"https://unpkg.com/htmx-ext-response-targets@2.0.0/response-targets.js\"></script>\n    <link href=\"../styles/styles.css\" rel=\"stylesheet\">\n</head>\n<body>\n    <main>\n        ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </main>\n    <div id=\"toastContainer\"></div>\n</body>\n<script type=\"module\" src=\"../scripts/toasts.js\"></script>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		gg.jte.Content content = (gg.jte.Content)params.get("content");
		String tabName = (String)params.get("tabName");
		render(jteOutput, jteHtmlInterceptor, content, tabName);
	}
}
