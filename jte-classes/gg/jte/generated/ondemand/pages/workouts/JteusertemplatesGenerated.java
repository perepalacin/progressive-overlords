package gg.jte.generated.ondemand.pages.workouts;
public final class JteusertemplatesGenerated {
	public static final String JTE_NAME = "pages/workouts/user-templates.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,2,2,2,2,13,13,15,15,17,17,18,18,18,18,21,21,21,21,22,22,22,25,25,25,25,25,25,25,25,25,28,28,28,28,28,28,28,28,28,31,31,31,31,31,31,31,31,31,32,32,32,32,37,37,37,37,37,37,37,37,43,43,43,43,43,43,43,43,43,47,47,47,51,51,51,55,55,57,57,61,61,61,63,63,63,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.pages.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"flex flex-col gap-2 w-full md:w-[600px]\">\n        <div class=\"flex flex-row w-full justify-between\">\n            <h1 class=\"text-xl font-semibold\">Your Templates</h1>\n            <button>\n                <a href=\"/create-template\">\n                    Add a new template\n                </a>\n            </button>\n        </div>\n        <div class=\"flex flex-col border rounded-md px-4 py-3 gap-2\">\n        ");
				if (templates.isEmpty()) {
					jteOutput.writeContent("\n            <p>You have no routines at the moment.</p>\n        ");
				} else {
					jteOutput.writeContent("\n            <ul class=\"flex flex-col gap-2\">\n                ");
					for (progressive_overlords.entities.dao.WorkoutDao template : templates) {
						jteOutput.writeContent("\n                    <li id=\"template-");
						jteOutput.setContext("li", "id");
						jteOutput.writeUserContent(template.getId());
						jteOutput.setContext("li", null);
						jteOutput.writeContent("\" class=\"flex flex-col items-start justify-center border rounded-md pl-6 pr-2 py-2\">\n                        <div class=\"flex flex-row relative justify-between w-full items-center\">\n                            <div class=\"flex flex-row gap-2 items-center\">\n                                <div class=\"w-[1rem] h-[1rem] rounded-md\" style=\"background-color: ");
						jteOutput.setContext("div", "style");
						jteOutput.writeUserContent(template.getColor());
						jteOutput.setContext("div", null);
						jteOutput.writeContent("\"></div>\n                                <p>");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(template.getName());
						jteOutput.writeContent("</p>\n                            </div>\n                            <div>\n                                <button class=\"accordion-button p-2 rounded-full hover:bg-gray-200 transition\"");
						var __jte_html_attribute_0 = template.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("button", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_0);
							jteOutput.setContext("button", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                    <img src=\"../icons/chevron-down.svg\" class=\"accordion-icon transition-transform duration-300 ease-in-out\" style=\"width: 1rem;\" />\n                                </button>\n                                <button class=\"menu-button p-2 rounded-full hover:bg-gray-200 transition\"");
						var __jte_html_attribute_1 = template.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("button", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_1);
							jteOutput.setContext("button", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                    <img src=\"../icons/ellipsis-vertical.svg\" style=\"width: 1rem\"/>\n                                </button>\n                                <div class=\"dropdown-menu hidden absolute top-0 right-0 mt-12 w-40 bg-white border rounded-lg shadow-lg z-50\"");
						var __jte_html_attribute_2 = template.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("div", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_2);
							jteOutput.setContext("div", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                                    <a href=\"/edit-template/");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(template.getId());
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\">\n                                        <button class=\"flex items-center gap-2 px-4 py-2 w-full text-left hover:bg-gray-100\">\n                                            <img src=\"../icons/pen.svg\" style=\"width: 1rem\"/> Edit\n                                        </button>\n                                    </a>\n                                    <button hx-delete=\"/api/v1/workouts/");
						jteOutput.setContext("button", "hx-delete");
						jteOutput.writeUserContent(template.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("\" hx-swap=\"outerHTML\" hx-target=\"#template-");
						jteOutput.setContext("button", "hx-target");
						jteOutput.writeUserContent(template.getId());
						jteOutput.setContext("button", null);
						jteOutput.writeContent("\" class=\"flex items-center gap-2 px-4 py-2 w-full text-left text-red-600 hover:bg-gray-100\">\n                                        <img src=\"../icons/trash.svg\" style=\"width: 1rem\"/> Delete\n                                    </button>\n                                </div>\n                            </div>\n                        </div>\n                        <div class=\"accordion hidden mt-2 w-full\"");
						var __jte_html_attribute_3 = template.getId();
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
							jteOutput.writeContent(" data-id=\"");
							jteOutput.setContext("div", "data-id");
							jteOutput.writeUserContent(__jte_html_attribute_3);
							jteOutput.setContext("div", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">\n                            <div class=\"mb-2 h-[1px] bg-gray-200\"></div>\n                            <p class=\"mb-2 text-md\">\n                                <span class=\"font-semibold\">Description: </span>\n                                ");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(template.getDescription());
						jteOutput.writeContent("\n                            </p>\n                            <p>\n                                <span class=\"font-semibold\">Tags: </span>\n                                ");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(template.getUnparsedTags());
						jteOutput.writeContent("\n                            </p>\n                        </div>\n                    </li>\n                ");
					}
					jteOutput.writeContent("\n            </ul>\n        ");
				}
				jteOutput.writeContent("\n        </div>\n    </div>\n    <script src=\"../scripts/accordion.js\"></script>\n");
			}
		}, "Routines");
		jteOutput.writeContent("\n\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<progressive_overlords.entities.dao.WorkoutDao> templates = (java.util.List<progressive_overlords.entities.dao.WorkoutDao>)params.get("templates");
		render(jteOutput, jteHtmlInterceptor, templates);
	}
}
