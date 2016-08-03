package com.company;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

	private static final String WS_ROOT = "/api/*";

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), WS_ROOT);
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyServletConfig.class.getName());
		return registration;
	}

	public static class JerseyServletConfig extends ResourceConfig {
		public JerseyServletConfig() {
			register(RequestContextFilter.class);
			packages("com.company");
			register(LoggingFilter.class);
		}
	}

}