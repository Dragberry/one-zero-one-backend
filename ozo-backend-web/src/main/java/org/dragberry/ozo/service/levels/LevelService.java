package org.dragberry.ozo.service.levels;

import org.dragberry.ozo.common.level.LevelConfig;
import org.dragberry.ozo.domain.Level;

public interface LevelService {
	
	LevelConfig getLevelConfig(String levelId);

	Level getLevel(String levelId);

}
