package configuration;

import freemarker.template.Configuration;
import java.io.StringWriter;
import java.util.Map;
import freemarker.template.Template;


public class FreeMarkerService {

    private Configuration cfg;

    public FreeMarkerService() {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(this.getClass(), 
        		"/resources/");
    }

    public String renderTemplate(String templateName, Map<String, Object> dataModel) throws Exception {
        Template template = cfg.getTemplate(templateName);
        try (StringWriter out = new StringWriter()) {
            template.process(dataModel, out);
            return out.toString();
        }
    }
}
