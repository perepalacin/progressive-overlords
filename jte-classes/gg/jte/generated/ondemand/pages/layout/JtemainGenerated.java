package gg.jte.generated.ondemand.pages.layout;
public final class JtemainGenerated {
	public static final String JTE_NAME = "pages/layout/main.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,7,7,7,7,37,37,37,47,47,47,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, gg.jte.Content content, String tabName) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"cat\">\n<head>\n    <meta charset=\"UTF-8\" name=\"htmx-config\" content='{\"responseHandling\": [{\"code\":\".*\", \"swap\": true}]}'>\n    <link rel=\"icon\" href=\"data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>🎓</text></svg>\">\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(tabName);
		jteOutput.writeContent(" - Progressive Overlords</title>\n    <script src=\"https://unpkg.com/htmx.org@2.0.3\"\n            integrity=\"sha384-0895/pl2MU10Hqc6jd4RvrthNlDiE9U1tWmX7WRESftEDRosgxNsQG/Ze9YMRzHq\"\n            crossorigin=\"anonymous\"></script>\n    <script src=\"https://unpkg.com/htmx-ext-response-targets@2.0.0/response-targets.js\"></script>\n    <script src=\"https://cdn.tailwindcss.com\"></script>\n    <link href=\"../styles/index.css\" rel=\"stylesheet\">\n</head>\n<body>\n<main class=\"w-full h-screen flex flex-row justify-between\">\n    <nav class=\"flex flex-col w-1/5 justify-between items-start border-r px-2 py-2 \">\n        <div class=\"flex flex-col gap-2\">\n            <a href=\"/\" class=\"flex flex-row gap-1 mb-2\">\n                <img src=\"https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg\" class=\"font-bold\"/>\n                <p class=\"text-xl font-semibold\">Progressive Overlords</p>\n            </a>\n            <a href=\"/\">Feed</a>\n            <a href=\"/templates\">Templates</a>\n        </div>\n        <div class=\"w-full flex flex-row justify-between\">\n            <div>\n                <img src=\"\"/>\n                <p>Username</p>\n            </div>\n            <button hx-post=\"/api/v1/auth/logout\" hx-swap=\"none\">\n                <img src=\"https://unpkg.com/lucide-static@latest/icons/log-out.svg\" class=\"w-2/3\"/>\n            </button>\n        </div>\n    </nav>\n    <section class=\"py-2\">\n        ");
		jteOutput.setContext("section", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </section>\n    <aside class=\"flex flex-col w-1/5\">\n        <p>Side section!</p>\n    </aside>\n    <div id=\"toastContainer\" class=\"fixed top-5 right-5 flex flex-col gap-2 z-50\"></div>\n</main>\n</body>\n<script type=\"module\" src=\"../scripts/dropdown.js\"></script>\n<script type=\"module\" src=\"../scripts/toasts.js\"></script>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		gg.jte.Content content = (gg.jte.Content)params.get("content");
		String tabName = (String)params.get("tabName");
		render(jteOutput, jteHtmlInterceptor, content, tabName);
	}
}
