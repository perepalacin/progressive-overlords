package gg.jte.generated.ondemand.pages;
public final class JtehomeGenerated {
	public static final String JTE_NAME = "pages/home.jte";
	public static final int[] JTE_LINE_INFO = {23,23,23,23,23,23,23,23,23,23,23};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("\n\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Login</title>\n    <script src=\"https://unpkg.com/htmx.org@1.9.10\"></script>\n    <script src=\"https://cdn.tailwindcss.com\"></script>\n</head>\n<body class=\"flex items-center justify-center h-screen bg-gray-100\">\n\n<p>Welcome to the home page of progressive overlords</p>\n<button\n        hx-post=\"/api/v1/auth/logout\"\n        hx-swap=\"none\"\n        class=\"bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700\">\n    Logout\n</button>\n\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
