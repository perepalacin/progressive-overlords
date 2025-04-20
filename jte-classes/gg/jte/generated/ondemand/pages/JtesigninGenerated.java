package gg.jte.generated.ondemand.pages;
public final class JtesigninGenerated {
	public static final String JTE_NAME = "pages/sign-in.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,0,33,33,33,35,35,35,35,35,35};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"w-full flex-column align-center justify-center mt-6 text-center gap-2\">\n            <div class=\"flex-row gap-1 align-center logo\">\n                <img style=\"width: 4rem; height: 4rem;\" src=\"https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg\" />\n                <h1 class=\"m-0\">Progressive Overlords</h1>\n            </div>\n            <h2 class=\"\">Welcome Back</h2>\n            <p class=\"\">Sign in to continue your fitness journey</p>\n\n            <form hx-post=\"/api/v1/auth/sign-in\"\n                  hx-target=\"#error-message\"\n                  hx-swap=\"innerHTML\"\n                  class=\"flex-column gap-2 justify-center\"\n            >\n                <div class=\"flex-row gap-2 align-center\">\n                    <label class=\"auth-form-label\" for=\"email\">Email</label>\n                    <input type=\"username\" id=\"username\" name=\"username\" placeholder=\"username\" required>\n                </div>\n\n                <div class=\"flex-row gap-2 align-center\">\n                    <label class=\"auth-form-label\" for=\"password\">Password</label>\n                    <input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Enter your password\" required>\n                </div>\n\n                <button type=\"submit\" class=\"button main-button\">Sign In</button>\n\n            </form>\n\n            <div class=\"signup-link\">\n                <p>Don't have an account yet? <a class=\"font-bold\" style=\"text-decoration: underline\" href=\"/sign-up\">Sign Up</a></p>\n            </div>\n            <div id=\"error-message\" class=\"text-red-600 text-center mt-3\"></div>\n    </div>\n");
			}
		}, "Sign in");
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
