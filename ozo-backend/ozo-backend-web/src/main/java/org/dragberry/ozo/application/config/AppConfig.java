package org.dragberry.ozo.application.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dragberry.ozo.domain.IntegerLevelResult;
import org.dragberry.ozo.service.Services;
import org.dragberry.ozo.service.LevelResultCacheServiceBean.ResultKey;
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
	public Map<ResultKey, IntegerLevelResult> levelResultsCache () {
		return new ConcurrentHashMap<>(); 
	}
}
