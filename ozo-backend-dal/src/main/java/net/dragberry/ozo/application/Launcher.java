package net.dragberry.ozo.application;

import java.time.LocalDateTime;

import org.dragberry.ozo.dao.LevelDao;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.StepsLevelResult;
import org.dragberry.ozo.domain.TimeLevelResult;
import org.dragberry.ozo.domain.Level;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.domain.LostUnitsLevelResult;
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
			
			LevelResultDao levelResultDao = context.getBean(LevelResultDao.class);
			createStepsResult(user0, level, 5, levelResultDao);
			createStepsResult(user1, level, 7, levelResultDao);
			createStepsResult(user2, level, 3, levelResultDao);
			createStepsResult(user3, level, 6, levelResultDao);
			
			createLostUnitsResult(user0, level, 53, levelResultDao);
			createLostUnitsResult(user1, level, 73, levelResultDao);
			createLostUnitsResult(user2, level, 553, levelResultDao);
			createLostUnitsResult(user3, level, 63, levelResultDao);
			
			createTimeResult(user0, level, 32.5f, levelResultDao);
			createTimeResult(user1, level, 25.77f, levelResultDao);
			createTimeResult(user2, level, 231f, levelResultDao);
			createTimeResult(user3, level, 54.76f, levelResultDao);
			
			level = levelDao.findOne(new LevelId("ozo.level.1"));
			
			createStepsResult(user0, level, 44, levelResultDao);
			createStepsResult(user1, level, 32, levelResultDao);
			createStepsResult(user2, level, 534, levelResultDao);
			createStepsResult(user3, level, 66, levelResultDao);
			
			createLostUnitsResult(user0, level, 43, levelResultDao);
			createLostUnitsResult(user1, level, 2, levelResultDao);
			createLostUnitsResult(user2, level, 21, levelResultDao);
			createLostUnitsResult(user3, level, 54, levelResultDao);
			
			createTimeResult(user0, level, 42.3f, levelResultDao);
			createTimeResult(user1, level, 233f, levelResultDao);
			createTimeResult(user2, level, 65.7f, levelResultDao);
			createTimeResult(user3, level, 98.4f, levelResultDao);
		

			level = levelDao.findOne(new LevelId("ozo.level.2"));
			
			createStepsResult(user0, level, 144, levelResultDao);
			createStepsResult(user1, level, 132, levelResultDao);
			createStepsResult(user2, level, 1534, levelResultDao);
			createStepsResult(user3, level, 166, levelResultDao);
			
			createLostUnitsResult(user0, level, 143, levelResultDao);
			createLostUnitsResult(user1, level, 12, levelResultDao);
			createLostUnitsResult(user2, level, 121, levelResultDao);
			createLostUnitsResult(user3, level, 154, levelResultDao);
			
			createTimeResult(user0, level, 142.3f, levelResultDao);
			createTimeResult(user1, level, 1233f, levelResultDao);
			createTimeResult(user2, level, 165.7f, levelResultDao);
			createTimeResult(user3, level, 198.4f, levelResultDao);
		
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
	
	private static void createLostUnitsResult(User user0, Level level, Integer value, LevelResultDao levelResultDao) {
		LevelResult<Integer> integerResult = new LostUnitsLevelResult();
		integerResult.setLevel(level);
		integerResult.setResultValue(value);
		integerResult.setUser(user0);
		integerResult.setDate(LocalDateTime.now());
		levelResultDao.create(integerResult);
	}
	
	private static void createTimeResult(User user0, Level level, Float value, LevelResultDao levelResultDao) {
		LevelResult<Float> floatResult = new TimeLevelResult();
		floatResult.setLevel(level);
		floatResult.setResultValue(value);
		floatResult.setUser(user0);
		floatResult.setDate(LocalDateTime.now());
		levelResultDao.create(floatResult);
	}
}
