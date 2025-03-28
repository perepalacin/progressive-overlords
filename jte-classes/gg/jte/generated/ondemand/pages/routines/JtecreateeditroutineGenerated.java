package gg.jte.generated.ondemand.pages.routines;
public final class JtecreateeditroutineGenerated {
	public static final String JTE_NAME = "pages/routines/create-edit-routine.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,0,1,1,2,2,3,3,3,3,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "routines");
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.routines.JteroutineformbuilderGenerated.render(jteOutput, jteHtmlInterceptor);
				jteOutput.writeContent("\n");
			}
		}, "Home");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
