package com.oddle.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.register(HibernateConfiguration.class);
		ctx.setServletContext(container);

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(ctx));

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
