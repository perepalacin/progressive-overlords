package gg.jte.generated.ondemand.components.exercises;
public final class JteexerciseuserdataGenerated {
	public static final String JTE_NAME = "components/exercises/exercise-user-data.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,7,7,7,8,8,10,10,14,14,14,18,18,18,22,22,22,25,25,29,29,31,31,31,34,34,36,36,36,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.ExerciseDao exercise, progressive_overlords.entities.dao.ExerciseUserDataDao userData, String chartValues) {
		jteOutput.writeContent("<li class=\"exercise-dropdown-item\" hx-trigger=\"click\" hx-get=\"/api/v1/exercises/data/");
		jteOutput.setContext("li", "hx-get");
		jteOutput.writeUserContent(exercise.getId());
		jteOutput.setContext("li", null);
		jteOutput.writeContent("\" hx-target=\"#exercise-data-container\" hx-swap=\"innerHTML\">\n    <img loading=\"lazy\"");
		var __jte_html_attribute_0 = exercise.getThumbnail();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" src=\"");
			jteOutput.setContext("img", "src");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("img", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\n    <h3>");
		jteOutput.setContext("h3", null);
		jteOutput.writeUserContent(exercise.getName());
		jteOutput.writeContent("</h3>\n</li>\n<p>");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(exercise.getDescription());
		jteOutput.writeContent("</p>\n");
		if (userData == null) {
			jteOutput.writeContent("\n    <h3 class=\"mt-2\">We have no data to show you. You haven't trained this exercise yet...</h3>\n");
		} else {
			jteOutput.writeContent("\n    <div class=\"exercise-kpis\">\n        <div>\n            <p>Max. Weight</p>\n            <h3 class=\"m-0\">");
			jteOutput.setContext("h3", null);
			jteOutput.writeUserContent(String.format("%.2f", userData.getMaxWeight()));
			jteOutput.writeContent(" kg<span class=\"font-light\"> (4 reps)</span></h3>\n        </div>\n        <div>\n            <p>Avg. reps<span class=\"font-light\"> (per set)</span></p>\n            <h3 class=\"m-0\">");
			jteOutput.setContext("h3", null);
			jteOutput.writeUserContent(String.format("%.2f", userData.getAvgReps()));
			jteOutput.writeContent("</h3>\n        </div>\n        <div>\n            <p>ERP<span class=\"font-light\"> (1 rep)</span></p>\n            <h3 class=\"m-0\">");
			jteOutput.setContext("h3", null);
			jteOutput.writeUserContent(String.format("%.2f", userData.getErp()));
			jteOutput.writeContent(" kg</h3>\n        </div>\n    </div>\n");
		}
		jteOutput.writeContent("\n<div id=\"chart-container\"></div>\n<script type=\"module\" src=\"../scripts/line-chart.js\"></script>\n<script type=\"module\">\n    ");
		if (chartValues != null && !chartValues.isEmpty()) {
			jteOutput.writeContent("\n        import { drawLineChart } from '../scripts/line-chart.js';\n        const chartValuesJsonString = '");
			jteOutput.setContext("script", null);
			jteOutput.writeUserContent(chartValues);
			jteOutput.writeContent("';\n        const chartValues = JSON.parse(chartValuesJsonString);\n        drawLineChart(chartValues);\n    ");
		}
		jteOutput.writeContent("\n</script>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.ExerciseDao exercise = (progressive_overlords.entities.dao.ExerciseDao)params.get("exercise");
		progressive_overlords.entities.dao.ExerciseUserDataDao userData = (progressive_overlords.entities.dao.ExerciseUserDataDao)params.get("userData");
		String chartValues = (String)params.get("chartValues");
		render(jteOutput, jteHtmlInterceptor, exercise, userData, chartValues);
	}
}
