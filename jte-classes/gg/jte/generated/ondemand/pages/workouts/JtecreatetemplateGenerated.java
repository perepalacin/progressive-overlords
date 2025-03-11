package gg.jte.generated.ondemand.pages.workouts;
public final class JtecreatetemplateGenerated {
	public static final String JTE_NAME = "pages/workouts/create-template.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,4,4,4,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,16,16,17,17,17,17,17,17,17,17,17,18,18,21,21,21,21,21,21,21,21,21,24,24,24,28,28,28,28,28,28,28,28,28,33,33,33,33,33,33,33,33,33,36,36,36,36,36,36,36,36,36,40,41,42,43,44,45,46,47,48,49,56,56,56,64,64,64,64,64,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao template) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.pages.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div  class=\"flex flex-col gap-2 w-full md:w-[600px]\">\n    <h1 class=\"text-xl font-semibold\">");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(template != null ? "Edit template" : "Create a template");
				jteOutput.writeContent("</h1>\n    <form\n            id=\"workoutForm\"\n           ");
				var __jte_html_attribute_0 = template == null ? "/api/v1/workouts/templates" : null;
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" hx-post=\"");
					jteOutput.setContext("form", "hx-post");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent("\n           ");
				var __jte_html_attribute_1 = template != null ? "/api/v1/workouts/templates" : null;
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" hx-patch=\"");
					jteOutput.setContext("form", "hx-patch");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent("\n            hx-trigger=\"submit\"\n            hx-swap=\"none\"\n            class=\"flex flex-col gap-2 px-4 py-2 rounded-md border\"\n            hx-headers='{\"Content-Type\": \"application/json\"}'\n            hx-ext=\"json-enc\"\n            onsubmit=\"event.preventDefault();\"\n    >\n        ");
				if (template != null) {
					jteOutput.writeContent("\n            <input style=\"display: none\" type=\"number\" name=\"id\" required");
					var __jte_html_attribute_2 = template.getId();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_2);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n        ");
				}
				jteOutput.writeContent("\n\n        <label class=\"block font-semibold\">Template Name:</label>\n        <input type=\"text\" name=\"name\" required class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"Enter template name\"");
				var __jte_html_attribute_3 = template != null ? template.getName() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_3);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n\n        <label class=\"block font-semibold\">Description:</label>\n        <textarea maxlength=\"511\" rows=\"3\" type=\"text\" name=\"description\" class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"Enter description\">");
				jteOutput.setContext("textarea", null);
				jteOutput.writeUserContent(template != null ? template.getDescription() : "");
				jteOutput.writeContent("</textarea>\n\n        <label class=\"block font-semibold\">Color:</label>\n        <div class=\"relative flex items-center gap-2\">\n            <input type=\"color\" name=\"color\" class=\"h-10 w-10 px-1 py-1 border rounded-md cursor-pointer\" id=\"colorPicker\"");
				var __jte_html_attribute_4 = template != null ? template.getColor() : "#00FF00";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_4);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n            <span id=\"colorHex\" class=\"font-mono text-sm\"></span>\n        </div>\n\n        <label class=\"block font-semibold\">Body Part:</label>\n        <input type=\"text\" name=\"bodyPart\" class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"e.g. arms, legs\"");
				var __jte_html_attribute_5 = template != null ? template.getBodyPart() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_5);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n\n        <label class=\"block font-semibold\">Tags (comma-separated):</label>\n        <input type=\"text\" name=\"unparsedTags\" class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"e.g. strength, endurance\"");
				var __jte_html_attribute_6 = template != null ? template.getUnparsedTags() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_6)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_6);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n\n        <div id=\"exerciseContainer\">\n            <h3 class=\"font-bold mt-4\">Exercises</h3>\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n");
				jteOutput.writeContent("\n                <div id=\"exerciseList\"></div>\n        </div>\n\n        <button type=\"button\" onclick=\"addExercise()\">+ Add Exercise</button>\n\n        <button type=\"submit\" class=\"bg-green-500 text-white px-3 py-2 rounded-md mt-4 w-full\">\n            ");
				jteOutput.setContext("button", null);
				jteOutput.writeUserContent(template == null ? "Create Template" : "Edit Template");
				jteOutput.writeContent("\n        </button>\n    </form>\n</div>\n<script src=\"../scripts/add-exercise.js\"></script>\n<script src=\"https://unpkg.com/htmx-ext-json-enc@2.0.1/json-enc.js\"></script>\n\n\n");
			}
		}, template != null ? "Edit template" : "Create a template");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao template = (progressive_overlords.entities.dao.WorkoutDao)params.get("template");
		render(jteOutput, jteHtmlInterceptor, template);
	}
}
