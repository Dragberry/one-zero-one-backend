package org.dragberry.ozo.dao;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.domain.IntegerLevelResult;
import org.dragberry.ozo.domain.LevelId;

public interface LevelResultDao extends DataAccessObject<IntegerLevelResult, Long> {

	IntegerLevelResult getLevelResult(LevelId levelId, LevelResultName name);
	
	IntegerLevelResult getLevelResultForUser(LevelId levelId, LevelResultName name, String userId);
}
