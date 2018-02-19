package edu.mum.configuration;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class DeploymentDescriptor extends AbstractAnnotationConfigDispatcherServletInitializer {
	  
	protected Class<?>[] getServletConfigClasses()  {
	    return new Class[] {Dispatcher.class};
	}
	
	protected String[] getServletMappings() {
	    return new String[] {"/"};
	}
	
	protected Class<?>[] getRootConfigClasses() {
	    return new Class[] {Persistence.class, Service.class, WebSecurityConfig.class};
	}
	
	// Allows Characters [ e.g. Chinese] in browser
    @Override
    protected Filter[] getServletFilters() {
      CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
      characterEncodingFilter.setEncoding("UTF-8");
      return new Filter[] { characterEncodingFilter};
    }
	
 }