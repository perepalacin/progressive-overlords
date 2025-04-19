package gg.jte.generated.ondemand.components.sets;
public final class JteworkoutsetGenerated {
	public static final String JTE_NAME = "components/sets/workout-set.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,11,11,11,11,11,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,20,20,21,21,21,21,27,27,34,34,37,37,37,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.SetDao set) {
		jteOutput.writeContent("<form");
		var __jte_html_attribute_0 = set.isCompleted() ? null : "/api/v1/sets";
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" hx-post=\"");
			jteOutput.setContext("form", "hx-post");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("form", null);
			jteOutput.writeContent("\"");
		}
		var __jte_html_attribute_1 = set.isCompleted() ? "/api/v1/sets/" + set.getId() : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" hx-patch=\"");
			jteOutput.setContext("form", "hx-patch");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("form", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" hx-target=\"this\" hx-swap=\"outerHTML\"");
		var __jte_html_attribute_2 = set.isCompleted() ? "workout-set-" + set.getId() : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" id=\"");
			jteOutput.setContext("form", "id");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("form", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n    <ul class=\"setTableRow\">\n        <li class=\"font-bold set-num\">\n            ");
		jteOutput.setContext("li", null);
		jteOutput.writeUserContent(set.getSetNum());
		jteOutput.writeContent("\n            <input class=\"hidden\" required");
		var __jte_html_attribute_3 = set.getWorkoutId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_3);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"workoutId\" />\n            <input class=\"hidden\" required");
		var __jte_html_attribute_4 = set.getExerciseId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_4);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"exerciseId\" />\n            <input class=\"hidden\" required");
		var __jte_html_attribute_5 = set.getSetNum();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_5);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"setNum\" />\n            <input class=\"hidden\" required");
		var __jte_html_attribute_6 = set.getExerciseNum();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_6)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_6);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"exerciseNum\" />\n        </li>\n        <li>\n            <input class=\"border-none\" name=\"warmup\"");
		var __jte_html_attribute_7 = set.isWarmup();
		if (__jte_html_attribute_7) {
		jteOutput.writeContent(" checked");
		}
		jteOutput.writeContent(" type=\"checkbox\">\n        </li>\n        <li>\n            <input type=\"number\" name=\"reps\"");
		var __jte_html_attribute_8 = set.getReps();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_8)) {
			jteOutput.writeContent(" placeholder=\"");
			jteOutput.setContext("input", "placeholder");
			jteOutput.writeUserContent(__jte_html_attribute_8);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		var __jte_html_attribute_9 = set.isCompleted() ? set.getReps() : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_9)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_9);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </li>\n        <li>\n            <input type=\"number\" name=\"weight\"");
		var __jte_html_attribute_10 = set.getWeight();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_10)) {
			jteOutput.writeContent(" placeholder=\"");
			jteOutput.setContext("input", "placeholder");
			jteOutput.writeUserContent(__jte_html_attribute_10);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		var __jte_html_attribute_11 = set.isCompleted() ? set.getWeight() : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_11)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_11);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" required>\n        </li>\n        <li class=\"flex-row gap-05\">\n            ");
		if (set.isCompleted()) {
			jteOutput.writeContent("\n                <button type=\"button\" hx-delete=\"/api/v1/sets/");
			jteOutput.setContext("button", "hx-delete");
			jteOutput.writeUserContent(set.getId());
			jteOutput.setContext("button", null);
			jteOutput.writeContent("\" hx-trigger=\"click\" hx-swap=\"none\" class=\"ghost-button-icon\" >\n                    <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                </button>\n                <button type=\"submit\"  class=\"ghost-button-icon\" >\n                    <img src=\"../icons/check.svg\" style=\"width: 1rem\" />\n                </button>\n            ");
		} else {
			jteOutput.writeContent("\n                <button type=\"button\"  class=\"ghost-button-icon remove-set-btn\" >\n                    <img src=\"../icons/x.svg\" style=\"width: 1rem\" />\n                </button>\n                <button type=\"submit\"  class=\"ghost-button-icon\" >\n                    <img src=\"../icons/send-horizontal.svg\" style=\"width: 1rem\" />\n                </button>\n            ");
		}
		jteOutput.writeContent("\n        </li>\n    </ul>\n</form>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.SetDao set = (progressive_overlords.entities.dao.SetDao)params.get("set");
		render(jteOutput, jteHtmlInterceptor, set);
	}
}
