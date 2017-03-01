package org.dragberry.ozo.service.levels;

import java.text.MessageFormat;

import org.dragberry.ozo.common.level.LevelConfig;
import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.dao.LevelDao;
import org.dragberry.ozo.domain.Level;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.web.exceptions.LevelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceBean implements LevelService {

	private static final String LEVEL_DB_INCONSISTENT_ERR_MSG = "Level {0} exists in the code, but it is absent in database!";
	
	private static final String LEVEL_404_MSG = "Level with id {0} does not exist!";
	
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
			return levelConfig;
		}
		throw new LevelNotFoundException(MessageFormat.format(LEVEL_404_MSG, levelId));
	}
	
	@Override
	public Level getLevel(String levelId) {
		LevelConfig levelConfig = Levels.LIST.get(levelId);
		Level level = null;
		if (levelConfig != null) {
			level = levelDao.findOne(new LevelId(levelId));
			if (level == null) {
				throw new RuntimeException(
						MessageFormat.format(LEVEL_DB_INCONSISTENT_ERR_MSG, levelId));
			}
			return level;
		}
		throw new LevelNotFoundException(MessageFormat.format(LEVEL_404_MSG, levelId));
	}

}
