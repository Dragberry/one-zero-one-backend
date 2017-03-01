package org.dragberry.ozo.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.dragberry.ozo.common.level.LevelConfig;
import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.common.levelresult.AllLevelResults;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.common.levelresult.LevelSingleResult;
import org.dragberry.ozo.common.levelresult.NewLevelResultsRequest;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.domain.User;
import org.dragberry.ozo.service.LevelResultCacheService;
import org.dragberry.ozo.service.levels.LevelService;
import org.dragberry.ozo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LevelResultController {

	@Autowired
	private LevelResultCacheService levelResultCacheService;
	
	@Autowired
	private LevelService levelService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LevelResultDao levelResultDao;
	
	@RequestMapping(value = "/level/result", method = RequestMethod.GET)
	@ResponseBody
	public AllLevelResults getResultsForLevel(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String levelId = request.getParameter("levelId");
		
		User user = userService.findUserById(userId);
		
		AllLevelResults allLevelResults = new AllLevelResults();
		allLevelResults.setUserName(user.getUserName());
		
		LevelConfig levelConfig = levelService.getLevelConfig(levelId);
		
		LevelId levelDomainId = new LevelId(levelId);
		LevelResults singleLevelResults = getLevelResults(userId, levelConfig, levelDomainId);
		allLevelResults.getLevelResults().put(levelConfig.getLevelId(), singleLevelResults);
		return allLevelResults;
	}
	
	@RequestMapping(value = "/level/result/all", method = RequestMethod.GET)
	@ResponseBody
	public AllLevelResults getResultsForAllLevels(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		
		User user = userService.findUserById(userId);
		
		AllLevelResults allLevelResults = new AllLevelResults();
		allLevelResults.setUserName(user.getUserName());
		
		Levels.LIST.values().forEach(levelConfig -> {
			LevelId levelId = new LevelId(levelConfig.getLevelId());
			LevelResults singleLevelResults = getLevelResults(userId, levelConfig, levelId);
			allLevelResults.getLevelResults().put(levelConfig.getLevelId(), singleLevelResults);
		});

		return allLevelResults;
	}

	private LevelResults getLevelResults(String userId, LevelConfig levelConfig, LevelId levelId) {
		LevelResults singleLevelResults = new LevelResults();
		
		levelConfig.getResultNames().forEach(resultName -> {
			LevelSingleResult<Integer> singleResult = new LevelSingleResult<>();
			LevelResult<Integer> result = null; 
			result = levelResultCacheService.computeIfAbsent(resultName, levelId, (resultKey) -> levelResultDao.getLevelResult(levelId, resultName));
			if (result != null) {
				singleResult.setWorlds(result.getResultValue());
				singleResult.setOwner(result.getUser().getUserName());
				if (!result.getUser().getUserId().equals(userId)) {
					result = levelResultDao.getLevelResultForUser(levelId, resultName, userId);
				}
				singleResult.setPersonal(result != null ? result.getResultValue() : null);
			}
			singleLevelResults.getResults().put(resultName, singleResult);
		});
		return singleLevelResults;
	}
	
	@RequestMapping(value = "/level/result/new", method = RequestMethod.POST)
	@ResponseBody
	public NewLevelResultsRequest newResult(@RequestBody NewLevelResultsRequest request) {
		User user = userService.findUserById(request.getUserId());
		
		LevelConfig levelConfig = levelService.getLevelConfig(request.getLevelId());
		
//		NewLevelResults newResult = new NewLevelResults();
//		newResult.setLevelId("ozo.level.0");
//		newResult.setUserId("id0");
//		newResult.getFloatResults().put(LevelResultName.TIME, new NewLevelResult<>(13f));
//		newResult.getIntegerResults().put(LevelResultName.STEPS, new NewLevelResult<>(14));
		return request;
	}
	

}