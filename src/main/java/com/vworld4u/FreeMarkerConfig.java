package com.vworld4u;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.utility.XmlEscape;

@Configuration
public class FreeMarkerConfig {

    @Bean
    public XmlEscape xmlEscape() {
    	return new XmlEscape();
    }
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(XmlEscape xmlEscape) {
    	FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//    	configurer.setTemplateLoaderPath("classpath:com.vworld4u.emailtemplates");
    	configurer.setTemplateLoaderPath("classpath:emailtemplates");
    	Map<String, Object> variables = new HashMap<>();
    	variables.put("xml_escape", xmlEscape);
		configurer.setFreemarkerVariables(variables);
		return configurer;
    }
}
