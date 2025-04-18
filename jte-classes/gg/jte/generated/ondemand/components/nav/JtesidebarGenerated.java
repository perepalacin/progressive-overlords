package gg.jte.generated.ondemand.components.nav;
public final class JtesidebarGenerated {
	public static final String JTE_NAME = "components/nav/sidebar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,10,10,10,10,10,10,10,10,10,10,14,14,14,14,14,14,14,14,14,18,18,18,18,18,18,18,18,18,22,22,22,22,22,22,22,22,22,29,29,38,38,38,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String activeTab) {
		jteOutput.writeContent("<section class=\"sidebar\">\n    <nav>\n        <a href=\"/\" class=\"logo\">\n            <img style=\"width: 2rem; height: 2rem;\" src=\"https://unpkg.com/lucide-static@latest/icons/chart-no-axes-column-increasing.svg\" />\n            <div>\n                <p>Progressive</p>\n                <p>Overlords</p>\n            </div>\n        </a>\n        <a");
		var __jte_html_attribute_0 = "feed".equals(activeTab) ? "active" : "";
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" class=\"");
			jteOutput.setContext("a", "class");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" href=\"/\">\n            <img src=\"../icons/dashboard.svg\" />\n            Feed\n        </a>\n        <a");
		var __jte_html_attribute_1 = "routines".equals(activeTab) ? "active" : "";
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" class=\"");
			jteOutput.setContext("a", "class");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" href=\"/routines\">\n            <img src=\"../icons/dumbell.svg\" />\n            Routines\n        </a>\n        <a");
		var __jte_html_attribute_2 = "records".equals(activeTab) ? "active" : "";
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" class=\"");
			jteOutput.setContext("a", "class");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" href=\"/records\">\n            <img src=\"../icons/chart-column.svg\" />\n            Records\n        </a>\n        <a");
		var __jte_html_attribute_3 = "history".equals(activeTab) ? "active" : "";
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
			jteOutput.writeContent(" class=\"");
			jteOutput.setContext("a", "class");
			jteOutput.writeUserContent(__jte_html_attribute_3);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" href=\"/history\">\n            <img src=\"../icons/history.svg\" />\n            History\n        </a>\n    </nav>\n    <div class=\"logout-section\">\n        <div class=\"flex-row align-center gap-05\">\n            ");
		gg.jte.generated.ondemand.components.friends.JteuserlogoGenerated.render(jteOutput, jteHtmlInterceptor, "perepalacin");
		jteOutput.writeContent("\n            <p>Username</p>\n        </div>\n        <button hx-post=\"/api/v1/auth/logout\" hx-swap=\"none\" class=\"ghost-button-icon\">\n            <img src=\"https://unpkg.com/lucide-static@latest/icons/log-out.svg\" style=\"width: 1.2rem\" />\n        </button>\n    </div>\n</section>\n<div class=\"sidebar-filler\">\n</div>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String activeTab = (String)params.get("activeTab");
		render(jteOutput, jteHtmlInterceptor, activeTab);
	}
}
