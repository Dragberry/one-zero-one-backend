package net.dragberry.ozo.application;

import java.time.LocalDateTime;

import org.dragberry.ozo.dao.LevelDao;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.StepsLevelResult;
import org.dragberry.ozo.domain.Level;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.dragberry.ozo.application.config.DataConfig;

public class Launcher {
	
	public static void main(String[] args) {
		try(ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class)) {
			UserDao userDao = context.getBean(UserDao.class);
			User user0 = userDao.findOne(1000L);
			User user1 = userDao.findOne(1001L);
			User user2 = userDao.findOne(1002L);
			User user3 = userDao.findOne(1003L);

			LevelDao levelDao = context.getBean(LevelDao.class);
			
			Level level = levelDao.findOne(new LevelId("ozo.level.0"));
			System.out.println(level.getEntityKey().getLevelId());
			
			LevelResultDao levelResultDao = context.getBean(LevelResultDao.class);
//			createStepsResult(user0, level, 5, levelResultDao);
//			createStepsResult(user1, level, 7, levelResultDao);
//			createStepsResult(user2, level, 3, levelResultDao);
//			createStepsResult(user3, level, 6, levelResultDao);
			
			level = levelDao.findOne(new LevelId("ozo.level.1"));
			System.out.println(level.getEntityKey().getLevelId());
			
			createStepsResult(user0, level, 44, levelResultDao);
			createStepsResult(user1, level, 32, levelResultDao);
			createStepsResult(user2, level, 534, levelResultDao);
			createStepsResult(user3, level, 66, levelResultDao);
		
		}
	}

	private static void createStepsResult(User user0, Level level, Integer value, LevelResultDao levelResultDao) {
		LevelResult<Integer> integerResult = new StepsLevelResult();
		integerResult.setLevel(level);
		integerResult.setResultValue(value);
		integerResult.setUser(user0);
		integerResult.setDate(LocalDateTime.now());
		levelResultDao.create(integerResult);
	}
}
