package gg.jte.generated.ondemand.pages.errors;
public final class Jte404Generated {
	public static final String JTE_NAME = "pages/errors/404.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,2,2,12,12,12,13,13,13,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String activeTab) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, activeTab);
				jteOutput.writeContent("\n    <div class=\"flex-column gap-1 px-2\">\n        <h1>Ups...</h1>\n        <p>The resource you are looking for doesn't exist!</p>\n        <button class=\"button main-button\" style=\"align-self: left\">\n            <a href=\"/\">\n            Go back home!\n            </a>\n        </button>\n    </div>\n");
			}
		}, "404");
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String activeTab = (String)params.get("activeTab");
		render(jteOutput, jteHtmlInterceptor, activeTab);
	}
}
