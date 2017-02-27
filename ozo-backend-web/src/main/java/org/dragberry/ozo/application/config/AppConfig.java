package org.dragberry.ozo.application.config;

import org.dragberry.ozo.service.Services;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.dragberry.ozo.application.config.DataConfig;

@Configuration
@Import(value = { DataConfig.class})
@ComponentScan(basePackageClasses = { Services.class })
public class AppConfig {

	@Bean
	public CommonAnnotationBeanPostProcessor postProcessor() {
		return new CommonAnnotationBeanPostProcessor();
	}
}
