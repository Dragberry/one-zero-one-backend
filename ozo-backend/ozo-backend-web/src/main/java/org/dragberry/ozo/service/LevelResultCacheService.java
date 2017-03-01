package org.dragberry.ozo.service;

import java.util.function.Function;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.domain.IntegerLevelResult;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.service.LevelResultCacheServiceBean.ResultKey;

public interface LevelResultCacheService {

	IntegerLevelResult getResultsForLevel(LevelResultName name, LevelId levelId);
		
	void putResultsForLevel(LevelResultName name, LevelId levelId, IntegerLevelResult levelResult);

	IntegerLevelResult computeIfAbsent(LevelResultName name, LevelId levelId, Function<? super ResultKey, ? extends IntegerLevelResult> mappingFunction);
}
