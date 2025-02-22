package gg.jte.generated.ondemand.responses.workouttemplates;
public final class JtetemplatecreatedGenerated {
	public static final String JTE_NAME = "responses/workout-templates/template-created.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,1,1,1,1,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.TemplateDao template) {
		jteOutput.writeContent("<p>Template created successfully! New template id: ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(template.getId());
		jteOutput.writeContent("</p>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.TemplateDao template = (progressive_overlords.entities.dao.TemplateDao)params.get("template");
		render(jteOutput, jteHtmlInterceptor, template);
	}
}
