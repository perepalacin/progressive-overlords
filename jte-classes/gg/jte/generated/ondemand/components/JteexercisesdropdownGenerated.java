package gg.jte.generated.ondemand.components;
public final class JteexercisesdropdownGenerated {
	public static final String JTE_NAME = "components/exercises-dropdown.jte";
	public static final int[] JTE_LINE_INFO = {1,1,1,1,3,3,3,3,3,7,7,7,7,10,10,10,10,13,13,13,13,16,16,16,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, int key) {
		jteOutput.writeContent("<div class=\"relative w-64\">\n    <input type=\"search\" id=\"dropdownInput-");
		jteOutput.setContext("input", "id");
		jteOutput.writeUserContent(key);
		jteOutput.setContext("input", null);
		jteOutput.writeContent("\"\n           name=\"query\"\n           autocomplete=\"off\"\n           hx-trigger=\"input changed delay:500ms, keyup[key=='Enter'], click\"\n           hx-get=\"/api/v1/exercises?page=0&inputId=dropdownList-");
		jteOutput.setContext("input", "hx-get");
		jteOutput.writeUserContent(key);
		jteOutput.setContext("input", null);
		jteOutput.writeContent("\"\n           class=\"w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500\"\n           placeholder=\"Select an exercise\"\n           hx-target=\"#dropdownList-");
		jteOutput.setContext("input", "hx-target");
		jteOutput.writeUserContent(key);
		jteOutput.setContext("input", null);
		jteOutput.writeContent("\"\n           hx-swap=\"innerHTML\"\n    >\n    <div id=\"dropdownList-");
		jteOutput.setContext("div", "id");
		jteOutput.writeUserContent(key);
		jteOutput.setContext("div", null);
		jteOutput.writeContent("\"\n         class=\"absolute w-full bg-white border border-gray-300 rounded-md shadow-md mt-1 max-h-40 overflow-y-auto\">\n    </div>\n</div>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		int key = (int)params.get("key");
		render(jteOutput, jteHtmlInterceptor, key);
	}
}
