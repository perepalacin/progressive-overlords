package gg.jte.generated.ondemand.responses.workouttemplates;
public final class JtetemplatecreatedGenerated {
	public static final String JTE_NAME = "responses/workout-templates/template-created.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao template) {
		jteOutput.writeContent("<p>Template with id: ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(template.getId());
		jteOutput.writeContent(" and name: ");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(template.getName());
		jteOutput.writeContent(" created successfully</p>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao template = (progressive_overlords.entities.dao.WorkoutDao)params.get("template");
		render(jteOutput, jteHtmlInterceptor, template);
	}
}
