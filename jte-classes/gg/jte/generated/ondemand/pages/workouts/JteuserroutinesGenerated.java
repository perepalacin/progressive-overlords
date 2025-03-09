package gg.jte.generated.ondemand.pages.workouts;
public final class JteuserroutinesGenerated {
	public static final String JTE_NAME = "pages/workouts/user-routines.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,6,6,11,11,13,13,15,15,15,17,17,19,19,23,23,23,25,25,25,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.pages.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"flex flex-col gap-2\">\n        <h1 class=\"text-xl font-semibold\">Your Routines</h1>\n        <div class=\"flex flex-col border rounded-md px-4 py-3 gap-2\">\n        ");
				if (templates.isEmpty()) {
					jteOutput.writeContent("\n            <p>You have no routines at the moment.</p>\n            <button>\n                Add a new template\n            </button>\n        ");
				} else {
					jteOutput.writeContent("\n            <ul class=\"flex flex-col gap-2\">\n                ");
					for (progressive_overlords.entities.dao.WorkoutDao template : templates) {
						jteOutput.writeContent("\n                    <li>\n                        <p>");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(template.getName());
						jteOutput.writeContent("</p>\n                    </li>\n                ");
					}
					jteOutput.writeContent("\n            </ul>\n        ");
				}
				jteOutput.writeContent("\n        </div>\n    </div>\n\n");
			}
		}, "Routines");
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates = (java.util.List<progressive_overlords.entities.dao.WorkoutDao>)params.get("templates");
		render(jteOutput, jteHtmlInterceptor, templates);
	}
}
