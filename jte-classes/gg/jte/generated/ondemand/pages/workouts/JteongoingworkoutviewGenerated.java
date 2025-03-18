package gg.jte.generated.ondemand.pages.workouts;
public final class JteongoingworkoutviewGenerated {
	public static final String JTE_NAME = "pages/workouts/ongoing-workout-view.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,5,5,5,7,7,7,8,8,8,8,15,15,15,15,15,15,15,15,15,18,18,19,19,23,23,23,23,23,23,23,23,23,27,27,27,27,27,27,27,27,27,27,27,27,27,28,28,28,28,28,28,28,28,28,38,38,38,38,38,38,38,38,38,38,38,38,38,39,39,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,43,43,44,44,44,44,44,44,44,44,44,45,45,46,46,46,46,46,46,46,46,46,47,47,47,47,47,47,47,47,47,48,48,48,48,48,48,48,48,48,49,49,49,49,49,49,49,49,49,52,52,52,55,55,55,55,55,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,58,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,61,64,64,65,65,65,65,68,68,72,72,74,74,76,76,81,81,85,85,85,85,85,85,85,85,85,89,89,91,91,102,102,102,108,108,108,110,110,110,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, progressive_overlords.entities.dao.WorkoutDao workout) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.pages.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n <div  class=\"flex flex-col gap-2 w-full md:w-[600px]\">\n  <div class=\"flex flex-row items-center justify-between\">\n   <h1 class=\"text-xl font-semibold\">");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(workout.getName());
				jteOutput.writeContent("</h1>\n   <div class=\"flex flex-row align-center items-center gap-4\">\n    <p id=\"timer\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(workout.getStartDate());
				jteOutput.writeContent("</p>\n    <button hx-swap=\"none\" hx-patch=\"/api/v1/workouts/finish/");
				jteOutput.setContext("button", "hx-patch");
				jteOutput.writeUserContent(workout.getId());
				jteOutput.setContext("button", null);
				jteOutput.writeContent("\" class=\"flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100\">\n     Finish Workout\n    </button>\n   </div>\n  </div>\n  <div class=\"flex flex-col gap-2 px-4 py-2 rounded-md border\">\n   <label class=\"block font-semibold\">Template Name:</label>\n   <input type=\"text\" name=\"name\" required class=\"w-full px-3 py-2 border rounded-md mb-2\" placeholder=\"Enter template name\"");
				var __jte_html_attribute_0 = workout != null ? workout.getName() : "";
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n   <div id=\"exerciseContainer\">\n    <h3 class=\"font-bold mt-4\">Exercises</h3>\n    ");
				if (workout != null && workout.getExercises() != null) {
					jteOutput.writeContent("\n     ");
					for (int i = 0; i < workout.getExercises().size(); i++) {
						jteOutput.writeContent("\n      <div class=\"exercise-item border px-4 py-2 mb-2 rounded-md\">\n       <div class=\"flex flex-row align-center justify-between py-2\">\n        <label>Exercise:</label>\n        <button type=\"button\"");
						var __jte_html_attribute_1 = i;
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
							jteOutput.writeContent(" data-exercise=\"");
							jteOutput.setContext("button", "data-exercise");
							jteOutput.writeUserContent(__jte_html_attribute_1);
							jteOutput.setContext("button", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(" class=\"remove-exercise-btn text-red-500 font-bold\">\n         <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n        </button>\n       </div>\n       <input type=\"number\" name=\"exerciseId-");
						jteOutput.setContext("input", "name");
						jteOutput.writeUserContent(i);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\" required class=\"border px-2 py-1 rounded-md w-full mb-2\"");
						var __jte_html_attribute_2 = workout.getExercises().get(i).getExerciseId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
							jteOutput.writeContent(" value=\"");
							jteOutput.setContext("input", "value");
							jteOutput.writeUserContent(__jte_html_attribute_2);
							jteOutput.setContext("input", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n       <div class=\"sets-container border-collapse border rounded-md\"");
						var __jte_html_attribute_3 = i;
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
							jteOutput.writeContent(" data-exercise=\"");
							jteOutput.setContext("div", "data-exercise");
							jteOutput.writeUserContent(__jte_html_attribute_3);
							jteOutput.setContext("div", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n        <section class=\"w-full\">\n         <ul class=\"flex flex-row items-center bg-gray-100\">\n          <li class=\"w-[10%] text-center p-2\">Set</li>\n          <li class=\"w-[20%] text-center p-2\">Warmup</li>\n          <li class=\"w-[25%] text-center p-2\">Reps</li>\n          <li class=\"w-[25%] text-center p-2\">Weight</li>\n          <li class=\"w-[10%] text-center p-2\"></li>\n          <li class=\"w-[10%] text-center p-2\"></li>\n         </ul>\n         <ul id=\"setTableBody-");
						jteOutput.setContext("ul", "id");
						jteOutput.writeUserContent(i);
						jteOutput.setContext("ul", null);
						jteOutput.writeContent("\"");
						var __jte_html_attribute_4 = workout.getExercises().get(i).getExerciseId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
							jteOutput.writeContent(" data-exercise-id=\"");
							jteOutput.setContext("ul", "data-exercise-id");
							jteOutput.writeUserContent(__jte_html_attribute_4);
							jteOutput.setContext("ul", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n         ");
						for (progressive_overlords.entities.dao.SetDao set : workout.getExercises().get(i).getSets()) {
							jteOutput.writeContent("\n          <form");
							var __jte_html_attribute_5 = set.isCompleted() ? null : "/api/v1/sets";
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_5)) {
								jteOutput.writeContent(" hx-post=\"");
								jteOutput.setContext("form", "hx-post");
								jteOutput.writeUserContent(__jte_html_attribute_5);
								jteOutput.setContext("form", null);
								jteOutput.writeContent("\"");
							}
							var __jte_html_attribute_6 = set.isCompleted() ? "/api/v1/sets": null;
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_6)) {
								jteOutput.writeContent(" hx-patch=\"");
								jteOutput.setContext("form", "hx-patch");
								jteOutput.writeUserContent(__jte_html_attribute_6);
								jteOutput.setContext("form", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(" hx-trigger=\"submit\" hx-target=\"this\" hx-swap=\"innerHTML\">\n           <li class=\"flex flex-row items-center\">\n\n            ");
							if (set.getId() != 0) {
								jteOutput.writeContent("\n             <input style=\"display: none\" type=\"number\" name=\"id\" required");
								var __jte_html_attribute_7 = set.getId();
								if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_7)) {
									jteOutput.writeContent(" value=\"");
									jteOutput.setContext("input", "value");
									jteOutput.writeUserContent(__jte_html_attribute_7);
									jteOutput.setContext("input", null);
									jteOutput.writeContent("\"");
								}
								jteOutput.writeContent(">\n            ");
							}
							jteOutput.writeContent("\n            <input style=\"display: none\" type=\"number\" name=\"exerciseNum\" required");
							var __jte_html_attribute_8 = set.getExerciseNum();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_8)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_8);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n            <input style=\"display: none\" type=\"number\" name=\"workoutId\" required");
							var __jte_html_attribute_9 = workout.getId();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_9)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_9);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n            <input style=\"display: none\" type=\"number\" name=\"exerciseId\" required");
							var __jte_html_attribute_10 = set.getExerciseId();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_10)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_10);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n            <input style=\"display: none\" type=\"number\" name=\"setNum\" required");
							var __jte_html_attribute_11 = set.getSetNum();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_11)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_11);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n\n            <div class=\"w-[10%] text-center p-2\">\n             ");
							jteOutput.setContext("div", null);
							jteOutput.writeUserContent(set.getSetNum() +1);
							jteOutput.writeContent("\n            </div>\n            <div class=\"w-[20%] text-center p-2\">\n             <input type=\"checkbox\" name=\"warmup\"");
							var __jte_html_attribute_12 = set.isWarmup();
							if (__jte_html_attribute_12) {
							jteOutput.writeContent(" checked");
							}
							jteOutput.writeContent(">\n            </div>\n            <div class=\"w-[25%] text-center p-2\">\n             <input type=\"number\" name=\"reps\" required class=\"border px-2 py-1 rounded-md w-full text-center\"");
							var __jte_html_attribute_13 = set.isCompleted() ? set.getReps() : null;
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_13)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_13);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							var __jte_html_attribute_14 = set.getReps();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_14)) {
								jteOutput.writeContent(" placeholder=\"");
								jteOutput.setContext("input", "placeholder");
								jteOutput.writeUserContent(__jte_html_attribute_14);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(" >\n            </div>\n            <div class=\"w-[25%] text-center p-2\">\n             <input type=\"number\" name=\"weight\" required class=\"border px-2 py-1 rounded-md w-full text-center\"");
							var __jte_html_attribute_15 = set.isCompleted() ? set.getWeight() : null;
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_15)) {
								jteOutput.writeContent(" value=\"");
								jteOutput.setContext("input", "value");
								jteOutput.writeUserContent(__jte_html_attribute_15);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(" ");
							var __jte_html_attribute_16 = set.getReps();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_16)) {
								jteOutput.writeContent(" placeholder=\"");
								jteOutput.setContext("input", "placeholder");
								jteOutput.writeUserContent(__jte_html_attribute_16);
								jteOutput.setContext("input", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n            </div>\n            <div class=\"w-[10%] text-center p-2 text-center\">\n             ");
							if (set.isCompleted()) {
								jteOutput.writeContent("\n             <button hx-delete=\"/api/v1/sets/");
								jteOutput.setContext("button", "hx-delete");
								jteOutput.writeUserContent(set.getId());
								jteOutput.setContext("button", null);
								jteOutput.writeContent("\" hx-target=\"this\" hx-swap=\"innerHTML\" class=\"text-red-500 font-bold\">\n              <img src=\"../icons/trash.svg\" style=\"width: 1rem\" />\n             </button>\n             ");
							}
							jteOutput.writeContent("\n            </div>\n            <div class=\"w-[10%] text-center p-2 text-center\">\n             <button type=\"submit\" class=\"text-red-500 font-bold\">\n              ");
							if (set.isCompleted()) {
								jteOutput.writeContent("\n               <img src=\"../icons/check.svg\" style=\"width: 1rem\" />\n              ");
							} else {
								jteOutput.writeContent("\n               <img src=\"../icons/send-horizontal.svg\" style=\"width: 1rem\" />\n              ");
							}
							jteOutput.writeContent("\n             </button>\n            </div>\n           </li>\n          </form>\n         ");
						}
						jteOutput.writeContent("\n         </ul>\n        </section>\n       </div>\n       <button type=\"button\"");
						var __jte_html_attribute_17 = i;
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_17)) {
							jteOutput.writeContent(" data-exercise=\"");
							jteOutput.setContext("button", "data-exercise");
							jteOutput.writeUserContent(__jte_html_attribute_17);
							jteOutput.setContext("button", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(" class=\"add-set-btn bg-blue-500 px-4 py-2 rounded-md\">\n        + Add Set\n       </button>\n      </div>\n     ");
					}
					jteOutput.writeContent("\n\n    ");
				}
				jteOutput.writeContent("\n    <div id=\"exerciseList\"></div>\n   </div>\n\n   <button type=\"button\" id=\"addExerciseBtn\">+ Add Exercise</button>\n\n\n  </div>\n </div>\n <script src=\"../scripts/timer.js\"></script>\n <script>\n  startElapsedTimer(\"");
				jteOutput.setContext("script", null);
				jteOutput.writeUserContent(workout.getStartDate());
				jteOutput.writeContent("\");\n </script>\n <script type=\"module\" src=\"../scripts/add-exercise.js\"></script>\n <script src=\"https://unpkg.com/htmx-ext-json-enc@2.0.1/json-enc.js\"></script>\n\n\n");
			}
		}, workout.getName());
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		progressive_overlords.entities.dao.WorkoutDao workout = (progressive_overlords.entities.dao.WorkoutDao)params.get("workout");
		render(jteOutput, jteHtmlInterceptor, workout);
	}
}
