package org.dragberry.ozo.web.controllers;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;

import org.dragberry.ozo.common.level.LevelConfig;
import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.common.levelresult.AllLevelResults;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.common.levelresult.NewLevelResultsRequest;
import org.dragberry.ozo.dao.UserDao;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.User;
import org.dragberry.ozo.service.LevelResultBuilderService;
import org.dragberry.ozo.service.levels.LevelService;
import org.dragberry.ozo.web.exceptions.LevelNotFoundException;
import org.dragberry.ozo.web.exceptions.UserNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LevelResultController {

	private static final String LEVEL_404_MSG = "Level with id {0} does not exist!";
	private static final String USER_404_MSG = "User with id {0} does not exist!";
	
	@Autowired
	private LevelResultBuilderService levelResultBuilderService;
	
	@Autowired
	private LevelService levelService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/level/result", method = RequestMethod.GET)
	@ResponseBody
	public AllLevelResults getResultsForLevel(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String levelId = request.getParameter("levelId");
		
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotFountException(MessageFormat.format(LEVEL_404_MSG, levelId));
		}
		
		AllLevelResults allLevelResults = new AllLevelResults();
		allLevelResults.setUserName(user.getUserName());
		
		LevelConfig levelConfig = levelService.getLevelConfig(levelId);
		if (levelConfig == null) {
			throw new LevelNotFoundException(MessageFormat.format(LEVEL_404_MSG, levelId));
		}
		
		LevelId levelDomainId = new LevelId(levelId);
		LevelResults singleLevelResults = new LevelResults();
		levelConfig.getResultNames().forEach(resultName -> {
			levelResultBuilderService.build(resultName, userId, levelDomainId, singleLevelResults);
		});
		
		allLevelResults.getLevelResults().put(levelConfig.getLevelId(), singleLevelResults);
		return allLevelResults;
	}
	
	@RequestMapping(value = "/level/result/all", method = RequestMethod.GET)
	@ResponseBody
	public AllLevelResults getResultsForAllLevels(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		
		User user = userDao.findById(userId);
		if (user == null) {
			throw new UserNotFountException(MessageFormat.format(USER_404_MSG, userId));
		}
		
		AllLevelResults allLevelResults = new AllLevelResults();
		allLevelResults.setUserName(user.getUserName());
		
		Levels.LIST.values().forEach(levelConfig -> {
			LevelId levelId = new LevelId(levelConfig.getLevelId());
			LevelResults singleLevelResults = new LevelResults();
			
			levelConfig.getResultNames().forEach(resultName -> {
				levelResultBuilderService.build(resultName, userId, levelId, singleLevelResults);
			});
			allLevelResults.getLevelResults().put(levelConfig.getLevelId(), singleLevelResults);
		});

		return allLevelResults;
	}
	
	@RequestMapping(value = "/level/result/new", method = RequestMethod.POST)
	@ResponseBody
	public NewLevelResultsRequest newResult(@RequestBody NewLevelResultsRequest request) {
		User user = userDao.findById(request.getUserId());
		if (user == null) {
			throw new UserNotFountException(MessageFormat.format(USER_404_MSG, request.getUserId()));
		}
		LevelConfig levelConfig = levelService.getLevelConfig(request.getLevelId());
		if (levelConfig == null) {
			throw new LevelNotFoundException(MessageFormat.format(LEVEL_404_MSG, request.getLevelId()));
		}

		
		
		
//		NewLevelResults newResult = new NewLevelResults();
//		newResult.setLevelId("ozo.level.0");
//		newResult.setUserId("id0");
//		newResult.getFloatResults().put(LevelResultName.TIME, new NewLevelResult<>(13f));
//		newResult.getIntegerResults().put(LevelResultName.STEPS, new NewLevelResult<>(14));
		return request;
	}
	

}