package org.dragberry.ozo.service.levels;

import java.text.MessageFormat;

import org.dragberry.ozo.common.level.LevelConfig;
import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.dao.LevelDao;
import org.dragberry.ozo.domain.Level;
import org.dragberry.ozo.domain.LevelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceBean implements LevelService {

	private static final String LEVEL_DB_INCONSISTENT_ERR_MSG = "Level {0} exists in the code, but it is absent in database!";
	
	@Autowired
	private LevelDao levelDao;
	
	@Override
	public LevelConfig getLevelConfig(String levelId) {
		LevelConfig levelConfig = Levels.LIST.get(levelId);
		Level level = null;
		if (levelConfig != null) {
			level = levelDao.findOne(new LevelId(levelId));
			if (level == null) {
				throw new RuntimeException(
						MessageFormat.format(LEVEL_DB_INCONSISTENT_ERR_MSG, levelId));
			}
		}
		return levelConfig;
	}

}
