package gg.jte.generated.ondemand.responses.exercises;
public final class JteexerciseheaderGenerated {
	public static final String JTE_NAME = "responses/exercises/exercise-header.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,3,3,5,5,5,5,5,5,5,5,5,6,6,8,8,8,8,11,11,11,11,11,11,11,11,11,11,11,11,11,15,15,15,15,15,15,15,15,15,17,17,17,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutExerciseDao workoutExercise, int workoutId, progressive_overlords.entities.dao.ExerciseDao exercise) {
		String randomId = "exercise-" + String.valueOf(new java.util.Random().nextInt(900000) + 100000);
		jteOutput.writeContent("\n\n<div class=\"flex-row align-center justify-between mb-1 px-1 exercise-header\"");
		var __jte_html_attribute_0 = randomId;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" id=\"");
			jteOutput.setContext("div", "id");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">\n    ");
		gg.jte.generated.ondemand.components.exercises.JteexercisepreviewGenerated.render(jteOutput, jteHtmlInterceptor, exercise);
		jteOutput.writeContent("\n    <div class=\"flex-row gap-025\">\n        <button type=\"button\" class=\"ghost-button-icon\" hx-get=\"/api/v1/exercises/selector\" hx-trigger=\"click\" hx-target=\"#");
		jteOutput.setContext("button", "hx-target");
		jteOutput.writeUserContent(randomId);
		jteOutput.setContext("button", null);
		jteOutput.writeContent("\" hx-swap=\"outerHTML\">\n            <img loading=\"lazy\" src=\"../icons/repeat-2.svg\" style=\"width: 1rem\" />\n        </button>\n        <button type=\"button\" class=\"ghost-button-icon ");
		jteOutput.setContext("button", "class");
		jteOutput.writeUserContent(workoutExercise != null ? "" : "remove-exercise-btn");
		jteOutput.setContext("button", null);
		jteOutput.writeContent("\"");
		var __jte_html_attribute_1 = workoutExercise != null ? "/api/v1/sets/exercise?workoutId=" + workoutId + "&exerciseNum=" + workoutExercise.getExerciseNum() + "&exerciseId=" + workoutExercise.getExerciseId() : null;
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" hx-delete=\"");
			jteOutput.setContext("button", "hx-delete");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("button", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" hx-swap=\"outerHTML\" hx-target=\"closest .exercise-item\">\n            <img loading=\"lazy\" src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n        </button>\n    </div>\n    <input class=\"hidden\"");
		var __jte_html_attribute_2 = exercise.getId();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" name=\"exerciseIds[]\" required />\n</div>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutExerciseDao workoutExercise = (progressive_overlords.entities.dao.WorkoutExerciseDao)params.get("workoutExercise");
		int workoutId = (int)params.get("workoutId");
		progressive_overlords.entities.dao.ExerciseDao exercise = (progressive_overlords.entities.dao.ExerciseDao)params.get("exercise");
		render(jteOutput, jteHtmlInterceptor, workoutExercise, workoutId, exercise);
	}
}
