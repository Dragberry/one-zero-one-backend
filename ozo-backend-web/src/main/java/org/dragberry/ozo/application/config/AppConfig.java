package org.dragberry.ozo.application.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.service.Services;
import org.dragberry.ozo.service.LevelResultCacheBean.ResultKey;
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
	
	@Bean
	public Map<ResultKey, LevelResult<?>> levelResultsCache () {
		return new ConcurrentHashMap<>(); 
	}
}
