package jee.fragment.thymeleaf;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@ApplicationScoped
public class ThymeleafConfiguration {
	
	private TemplateEngine templateEngine;
	
	private String prefix = "WEB-INF/templates/";
	private String suffix = ".html";
	private Long cacheTTLMs = 3600000L;
	private String templateMode = "HTML";
		
	public void onServletContextInit(@Observes @Initialized(ApplicationScoped.class) ServletContext servletContext) {
		ServletContextTemplateResolver templateResolver = 
		        new ServletContextTemplateResolver(servletContext);
		
		templateResolver.setTemplateMode(TemplateMode.parse(templateMode));
		templateResolver.setPrefix(prefix);	
		templateResolver.setSuffix(suffix);
		templateResolver.setCacheTTLMs(cacheTTLMs);
		
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		
	}
	
	@Produces
	public TemplateEngine getTemplateEngine() {
		return this.templateEngine;
	}

}