package config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This is servlet initializer class replace for web.xml file.
 * 
 * @author Sarath
 */
public class ServletContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addFilter("openSessionInViewFilter", openSessionInViewFilter()).addMappingForUrlPatterns(null,
				false, "/*");
		servletContext.addFilter("characterEncodingFilter", characterEncodingFilter()).addMappingForUrlPatterns(null,
				true, "/*");
		servletContext.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter()).addMappingForUrlPatterns(null,
				true, "/*");

		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain",
				DelegatingFilterProxy.class);
		securityFilter.addMappingForUrlPatterns(null, false, "/*");
		super.onStartup(servletContext);

	}

	/**
	 * Common configuration.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationContext.class, SecurityConfig.class };
	}

	/**
	 * SpringMVC configuration.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { SpringMVCApplicationContext.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletName()
	 */
	@Override
	protected String getServletName() {
		return "pfchoice";
	}

	@Override
	protected boolean isAsyncSupported() {
		return true;
	}

	/**
	 * @return
	 */
	private Filter openSessionInViewFilter() {
		return new OpenSessionInViewFilter();
	}

	/**
	 * @return
	 */
	private Filter characterEncodingFilter() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		return cef;
	}

	/**
	 * @return
	 */
	private Filter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

}
