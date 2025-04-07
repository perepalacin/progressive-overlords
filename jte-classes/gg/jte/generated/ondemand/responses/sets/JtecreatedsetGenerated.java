package gg.jte.generated.ondemand.responses.sets;
public final class JtecreatedsetGenerated {
	public static final String JTE_NAME = "responses/sets/created-set.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,5,5,5,5,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,12,12,12,12,12,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,29,29,29,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.SetDao set) {
		jteOutput.writeContent("\n<form hx-patch=\"/api/v1/sets\" hx-target=\"this\" hx-swap=\"outerHTML\">\n    <ul class=\"setTableRow\">\n        <li class=\"font-bold set-num\">\n            ");
		jteOutput.setContext("li", null);
		jteOutput.writeUserContent(set.getSetNum() +1);
		jteOutput.writeContent("\n            <input class=\"hidden\" required");
		var __jte_html_attribute_0 = set.getWorkoutId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"workoutId\"/>\n            <input class=\"hidden\" required");
		var __jte_html_attribute_1 = set.getExerciseId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"exerciseId\"/>\n            <input class=\"hidden\" required");
		var __jte_html_attribute_2 = set.getSetNum();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"setNum\"/>\n            <input class=\"hidden\" required");
		var __jte_html_attribute_3 = set.getId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_3);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"id\"/>\n        </li>\n        <li>\n            <input class=\"border-none\" name=\"warmup\"");
		var __jte_html_attribute_4 = set.isWarmup();
		if (__jte_html_attribute_4) {
		jteOutput.writeContent(" checked");
		}
		jteOutput.writeContent(" type=\"checkbox\">\n        </li>\n        <li>\n            <input type=\"number\" name=\"reps\"");
		var __jte_html_attribute_5 = set.getReps();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
			jteOutput.writeContent(" placeholder=\"");
			jteOutput.setContext("input", "placeholder");
			jteOutput.writeUserContent(__jte_html_attribute_5);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		var __jte_html_attribute_6 = set.getReps();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_6)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_6);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </li>\n        <li>\n            <input type=\"number\" name=\"weight\"");
		var __jte_html_attribute_7 = set.getWeight();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_7)) {
			jteOutput.writeContent(" placeholder=\"");
			jteOutput.setContext("input", "placeholder");
			jteOutput.writeUserContent(__jte_html_attribute_7);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		var __jte_html_attribute_8 = set.getWeight();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_8)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_8);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </li>\n        <li class=\"flex-row gap-05\">\n            <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n            </button>\n            <button type=\"submit\"  class=\"ghost-button-icon\" >\n                <img src=\"../icons/check.svg\" style=\"width: 1rem\" />\n            </button>\n        </li>\n    </ul>\n</form>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.SetDao set = (progressive_overlords.entities.dao.SetDao)params.get("set");
		render(jteOutput, jteHtmlInterceptor, set);
	}
}
