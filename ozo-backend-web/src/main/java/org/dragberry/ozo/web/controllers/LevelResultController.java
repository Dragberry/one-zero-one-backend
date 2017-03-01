package org.dragberry.ozo.web.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.dragberry.ozo.common.level.LevelConfig;
import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.common.levelresult.AllLevelResults;
import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.common.levelresult.LevelSingleResult;
import org.dragberry.ozo.common.levelresult.NewLevelResultResponse;
import org.dragberry.ozo.common.levelresult.NewLevelResultsRequest;
import org.dragberry.ozo.common.levelresult.NewLevelResultsResponse;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.IntegerLevelResult;
import org.dragberry.ozo.domain.Level;
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
	public NewLevelResultsResponse newResult(@RequestBody NewLevelResultsRequest request) {
		User user = userService.findUserById(request.getUserId());
		Level level = levelService.getLevel(request.getLevelId());
		
		NewLevelResultsResponse response = new NewLevelResultsResponse();
		response.setLevelId(request.getLevelId());
		response.setUserId(request.getUserId());
		
		request.getResults().entrySet().forEach(entry -> {
			LevelResultName resultName = entry.getKey();
			Integer resultValueRequest = entry.getValue().getValue();
			
			IntegerLevelResult worldResult = levelResultCacheService.computeIfAbsent(
					resultName, level.getEntityKey(), 
					(resultKey) -> levelResultDao.getLevelResult(level.getEntityKey(), resultName));
			if (worldResult == null) {
				// No world record for the level
				worldResult = new IntegerLevelResult();
				worldResult.setDate(LocalDateTime.now());
				worldResult.setUser(user);
				worldResult.setLevel(level);
				worldResult.setName(resultName);
				worldResult.setResultValue(resultValueRequest);
				worldResult = levelResultDao.create(worldResult);
				levelResultCacheService.putResultsForLevel(resultName, level.getEntityKey(), worldResult);
				response.getResults().put(resultName, new NewLevelResultResponse<>(resultValueRequest, true, true));
			} else if (worldResult.getResultValue() > resultValueRequest) {
				// World record is beaten
				worldResult.setResultValue(resultValueRequest);
				worldResult.setUser(user);
				worldResult.setDate(LocalDateTime.now());
				worldResult = levelResultDao.update(worldResult);
				levelResultCacheService.putResultsForLevel(resultName, level.getEntityKey(), worldResult);
				response.getResults().put(resultName, new NewLevelResultResponse<>(resultValueRequest, true, true));
			} else {
				IntegerLevelResult personalResult = levelResultDao.getLevelResultForUser(level.getEntityKey(), resultName, user.getUserId());
				if (personalResult == null) {
					// World record exists, personal is not exists
					personalResult = new IntegerLevelResult();
					personalResult.setDate(LocalDateTime.now());
					personalResult.setUser(user);
					personalResult.setLevel(level);
					personalResult.setName(resultName);
					personalResult.setResultValue(resultValueRequest);
					personalResult = levelResultDao.create(personalResult);
					response.getResults().put(resultName, new NewLevelResultResponse<>(resultValueRequest, false, true));
				} else if (personalResult.getResultValue() > resultValueRequest) {
					// World record exists, personal is beaten
					personalResult.setResultValue(resultValueRequest);
					personalResult.setUser(user);
					personalResult.setDate(LocalDateTime.now());
					personalResult = levelResultDao.update(personalResult);
					response.getResults().put(resultName, new NewLevelResultResponse<>(resultValueRequest, false, true));
				} else {
					// World record exists, personal is not beaten
					response.getResults().put(resultName, new NewLevelResultResponse<>(resultValueRequest, false, false));
				}
				
			}
		});
		return response;
	}
	

}