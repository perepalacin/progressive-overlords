package gg.jte.generated.ondemand.pages;
public final class JtesigninGenerated {
	public static final String JTE_NAME = "pages/sign-in.jte";
	public static final int[] JTE_LINE_INFO = {42,42,42,42,42,42,42,42,42,42,42};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Login</title>\n    <script src=\"https://unpkg.com/htmx.org@1.9.10\"></script>\n    <script src=\"https://cdn.tailwindcss.com\"></script>\n</head>\n<body class=\"flex items-center justify-center h-screen bg-gray-100\">\n\n<div class=\"bg-white p-6 rounded-lg shadow-lg w-96\">\n    <h2 class=\"text-2xl font-bold text-center mb-4\">Login</h2>\n\n    <form hx-post=\"/api/v1/auth/sign-in\"\n          hx-target=\"#error-message\"\n          hx-swap=\"innerHTML\"\n          class=\"space-y-4\">\n\n        <div>\n            <label class=\"block text-gray-700\">Username</label>\n            <input type=\"text\" name=\"username\" required\n                   class=\"w-full px-3 py-2 border rounded-lg focus:ring focus:ring-indigo-200\">\n        </div>\n\n        <div>\n            <label class=\"block text-gray-700\">Password</label>\n            <input type=\"password\" name=\"password\" required\n                   class=\"w-full px-3 py-2 border rounded-lg focus:ring focus:ring-indigo-200\">\n        </div>\n\n        <button type=\"submit\"\n                class=\"w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700\">\n            Sign In\n        </button>\n    </form>\n\n    <div id=\"error-message\" class=\"text-red-600 text-center mt-3\"></div>\n</div>\n\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
