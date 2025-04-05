package gg.jte.generated.ondemand.pages.routines;
public final class JtecreateeditroutineGenerated {
	public static final String JTE_NAME = "pages/routines/create-edit-routine.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,2,2,3,3,4,4,4,4,4,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao routine) {
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "routines");
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.routines.JteroutineformbuilderGenerated.render(jteOutput, jteHtmlInterceptor, routine);
				jteOutput.writeContent("\n");
			}
		}, "Create routine");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao routine = (progressive_overlords.entities.dao.WorkoutDao)params.get("routine");
		render(jteOutput, jteHtmlInterceptor, routine);
	}
}
