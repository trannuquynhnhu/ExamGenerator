package edu.mum.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {
	
	@Autowired Dispatcher dispatcher;
	@Autowired TilesConfig tiles;

	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(flowRegistry()).build();
	}
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
               .setBasePath("/WEB-INF/flows")
               .addFlowLocation("/assessment/assessment-flow.xml", "assessments/start")
                .build();
   
	}
   
   @Bean
   public FlowHandlerMapping flowHandlerMapping() {
       FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
//       handlerMapping.setOrder(-1);
       handlerMapping.setFlowRegistry(flowRegistry());
       return handlerMapping;
   }

   @Bean
   public FlowHandlerAdapter flowHandlerAdapter() {
       FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
       handlerAdapter.setFlowExecutor(flowExecutor());
       handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
       return handlerAdapter;
       }
   
	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder()
				.setViewFactoryCreator(mvcViewFactoryCreator())
				.setValidator(dispatcher.validator())
				.setDevelopmentMode(true)                  // Hot Reload on changes
				.build();
	}
	
	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(this.tiles.viewResolver()));
		factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}

}
