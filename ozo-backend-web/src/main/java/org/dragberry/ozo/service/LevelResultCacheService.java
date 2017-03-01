package org.dragberry.ozo.service;

import java.util.function.Function;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.service.LevelResultCacheServiceBean.ResultKey;

public interface LevelResultCacheService {

	LevelResult<Integer> getResultsForLevel(LevelResultName name, LevelId levelId);
		
	void putResultsForLevel(LevelResultName name, LevelId levelId, LevelResult<Integer> levelResult);

	LevelResult<Integer> computeIfAbsent(LevelResultName name, LevelId levelId, Function<? super ResultKey, ? extends LevelResult<Integer>> mappingFunction);
}
