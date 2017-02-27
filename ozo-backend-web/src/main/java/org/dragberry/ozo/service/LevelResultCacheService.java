package org.dragberry.ozo.service;

import java.util.function.Function;

import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.service.LevelResultCacheBean.ResultKey;

public interface LevelResultCacheService {

	<T extends LevelResult<?>> T getResultsForLevel(Class<? extends LevelResult<?>> clazz, LevelId levelId);
	
	<T extends LevelResult<?>> void putResultsForLevel(Class<? extends LevelResult<?>> clazz, LevelId levelId, T levelResult);

	<T extends LevelResult<?>> T computeIfAbsent(Class<? extends LevelResult<?>> clazz, LevelId levelId, Function<? super ResultKey, ? extends LevelResult<?>> mappingFunction);
	
}
