package edu.mum.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 *  Register the springSecurityFilterChain Filter for every URL in your application. 
 *  The WebSecurityConfig was loaded in our existing ApplicationInitializer
 */
public class WebSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
