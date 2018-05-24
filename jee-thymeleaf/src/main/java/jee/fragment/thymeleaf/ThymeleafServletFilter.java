package jee.fragment.thymeleaf;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;

@WebFilter(filterName = "templatingFilter", urlPatterns = "*.html", dispatcherTypes = { DispatcherType.FORWARD })
public class ThymeleafServletFilter implements Filter {

	@Inject
	private TemplateEngine templateEngine;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		IWebContext webContext = new WebContext(req, res, request.getServletContext());
		templateEngine.process(getTemplateName(req), webContext, response.getWriter());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	protected String getTemplateName(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (contextPath == null) {
			contextPath = "";
		}
		return requestPath.substring(contextPath.length());
	}

}
