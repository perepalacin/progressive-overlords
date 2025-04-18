package gg.jte.generated.ondemand.pages.routines;
public final class JteroutineslistGenerated {
	public static final String JTE_NAME = "pages/routines/routines-list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,3,3,16,16,18,18,20,20,21,21,21,21,23,23,23,23,24,24,24,27,27,28,28,28,28,28,28,28,28,28,31,31,32,32,32,32,32,32,32,32,32,35,35,35,35,35,35,35,35,35,36,36,36,36,36,36,36,36,36,36,36,36,37,37,37,37,42,42,42,42,42,42,42,42,48,48,48,48,48,48,48,48,48,50,50,53,53,53,55,55,59,59,61,61,67,67,67,67,67,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutDao> routines) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    ");
				gg.jte.generated.ondemand.components.nav.JtesidebarGenerated.render(jteOutput, jteHtmlInterceptor, "routines");
				jteOutput.writeContent("\n    <div id=\"workoutForm\">\n        <div class=\"flex-row align-center justify-between\">\n            <h1>Your Routines</h1>\n            <div class=\"flex-row align-center gap-1\">\n                <a href=\"/create-routine\">\n                    <button class=\"button main-button gap-05\">\n                        + Create new routine\n                    </button>\n                </a>\n            </div>\n        </div>\n        <div>\n            ");
				if (routines.isEmpty()) {
					jteOutput.writeContent("\n                <p>You have no routines. Create one to start tracking your workouts.</p>\n            ");
				} else {
					jteOutput.writeContent("\n                <ul class=\"routines-list\">\n                    ");
					for (progressive_overlords.entities.dao.WorkoutDao routine : routines) {
						jteOutput.writeContent("\n                        <li id=\"routine-");
						jteOutput.setContext("li", "id");
						jteOutput.writeUserContent(routine.getId());
						jteOutput.setContext("li", null);
						jteOutput.writeContent("\">\n                            <div class=\"routine-header\">\n                                    <a href=\"/routine/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(routine.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">\n                                        <p class=\"routine-name\">");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(routine.getName());
						jteOutput.writeContent("</p>\n                                    </a>\n                                <div class=\"flex-row gap-025 relative\">\n                                    ");
						if (routine.getDescription() != null) {
							jteOutput.writeContent("\n                                        <button class=\"accordion-button ghost-button-icon\"");
							var __jte_html_attribute_0 = routine.getId();
							if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
								jteOutput.writeContent(" data-id=\"");
								jteOutput.setContext("button", "data-id");
								jteOutput.writeUserContent(__jte_html_attribute_0);
								jteOutput.setContext("button", null);
								jteOutput.writeContent("\"");
							}
							jteOutput.writeContent(">\n                                            <img src=\"../icons/chevron-down.svg\" class=\"accordion-icon\" style=\"width: 1rem\"/>\n                                        </button>\n                                    ");
						}
						jteOutput.writeContent("\n                                    <button class=\"menu-button ghost-button-icon \"");
						var __jte_html_attribute_1 = routine.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("button", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_1);
							jteOutput.setContext("button", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                        <img src=\"../icons/ellipsis-vertical.svg\" style=\"width: 1rem\"/>\n                                    </button>\n                                    <div class=\"dropdown-menu flex-column hidden\"");
						var __jte_html_attribute_2 = routine.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("div", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_2);
							jteOutput.setContext("div", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                        <button hx-post=\"/api/v1/workouts/start?name=");
						jteOutput.setContext("button", "hx-post");
						jteOutput.writeUserContent(routine.getName());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("&description=");
						jteOutput.setContext("button", "hx-post");
						jteOutput.writeUserContent(routine.getDescription());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("&templateId=");
						jteOutput.setContext("button", "hx-post");
						jteOutput.writeUserContent(routine.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("&isTemplate=false\" class=\"dropdown-item\"><img src=\"../icons/send-horizontal.svg\" style=\"width: 1rem\"/>Start</button>\n                                        <a href=\"/create-routine/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(routine.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">\n                                            <button class=\"dropdown-item\">\n                                                <img src=\"../icons/pen.svg\" style=\"width: 1rem\"/> Edit\n                                            </button>\n                                        </a>\n                                        <button hx-delete=\"/api/v1/routines/");
						jteOutput.setContext("button", "hx-delete");
						jteOutput.writeUserContent(routine.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("?redirect=false\" hx-swap=\"outerHTML\" hx-target=\"#routine-");
						jteOutput.setContext("button", "hx-target");
						jteOutput.writeUserContent(routine.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("\" class=\"dropdown-item delete\">\n                                            <img src=\"../icons/trash.svg\" style=\"width: 1rem\"/> Delete\n                                        </button>\n                                    </div>\n                                </div>\n                            </div>\n                            <div class=\"accordion hidden\"");
						var __jte_html_attribute_3 = routine.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("div", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_3);
							jteOutput.setContext("div", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                <div class=\"routine-description-box\">\n                                ");
						if (routine.getDescription() != null) {
							jteOutput.writeContent("\n                                    <hr class=\"separator\" />\n                                    <p>\n                                        <span class=\"font-bold mr-1\">Description: </span> ");
							jteOutput.setContext("p", null);
							jteOutput.writeUserContent(routine.getDescription());
							jteOutput.writeContent("\n                                    </p>\n                                ");
						}
						jteOutput.writeContent("\n                                </div>\n                            </div>\n                        </li>\n                    ");
					}
					jteOutput.writeContent("\n                </ul>\n            ");
				}
				jteOutput.writeContent("\n        </div>\n    </div>\n\n    <script src=\"../scripts/dropdowns.js\"></script>\n    <script src=\"../scripts/accordion.js\"></script>\n");
			}
		}, "Routines");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutDao> routines = (java.util.List<progressive_overlords.entities.dao.WorkoutDao>)params.get("routines");
		render(jteOutput, jteHtmlInterceptor, routines);
	}
}
