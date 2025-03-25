package gg.jte.generated.ondemand.pages;
public final class JtesigninGenerated {
	public static final String JTE_NAME = "pages/sign-in.jte";
	public static final int[] JTE_LINE_INFO = {48,48,48,48,48,48,48,48,48,48,48};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Login</title>\n    <link rel=\"stylesheet\" href=\"../styles/styles.css\">\n    <script src=\"https://unpkg.com/htmx.org@1.9.10\"></script>\n</head>\n<body >\n\n\n<div class=\"login-container\">\n    <div class=\"login-card\">\n        <div class=\"logo\">\n            <h1>Progressive Overlords</h1>\n        </div>\n\n        <h2 class=\"login-title\">Welcome Back</h2>\n        <p class=\"login-subtitle\">Sign in to continue your fitness journey</p>\n\n        <form hx-post=\"/api/v1/auth/sign-in\"\n              hx-target=\"#error-message\"\n              hx-swap=\"innerHTML\"\n              class=\"login-form\"\n        >\n            <div class=\"form-group\">\n                <label for=\"email\">Email</label>\n                <input type=\"username\" id=\"username\" name=\"username\" placeholder=\"username\" required>\n            </div>\n\n            <div class=\"form-group\">\n                <label for=\"password\">Password</label>\n                <input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Enter your password\" required>\n            </div>\n\n            <button type=\"submit\" class=\"login-button\">Sign In</button>\n\n        </form>\n\n        <div class=\"signup-link\">\n            <p>Don't have an account yet? <a href=\"/register\">Sign Up</a></p>\n        </div>\n    </div>\n</div>\n\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
