package org.dragberry.ozo.service;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.domain.LevelId;

public interface LevelResultBuilderService {

	void build(LevelResultName resultName, String userId, LevelId levelId, LevelResults singleLevelResults);

}
