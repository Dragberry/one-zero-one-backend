package org.dragberry.ozo.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.common.levelresult.AllLevelResults;
import org.dragberry.ozo.common.levelresult.LevelSingleResult;
import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LostUnitsLevelResult;
import org.dragberry.ozo.domain.StepsLevelResult;
import org.dragberry.ozo.domain.TimeLevelResult;
import org.dragberry.ozo.service.LevelResultCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LevelResultController {

	@Autowired
	private LevelResultDao levelResultDao;
	
	@Autowired
	private LevelResultCacheService levelResultCache;
	
	@RequestMapping(value = "/result/level/all", method = RequestMethod.GET)
	@ResponseBody
	public AllLevelResults getResultsForAllLevels(HttpServletRequest request) {
		String userEmail = request.getParameter("userEmail");
		
		AllLevelResults allLevelResults = new AllLevelResults();
		allLevelResults.setUserEmail(userEmail);
		
		Levels.LIST.forEach(level -> {
			LevelId levelId = new LevelId(level.getLevelId());
			LevelResults singleLevelResults = new LevelResults();
			if (level.getResultNames().contains(LevelResultName.TIME)) {
				LevelSingleResult<Float> singleResult = new LevelSingleResult<Float>();
				TimeLevelResult result = null; 
				
				result = levelResultCache.computeIfAbsent(TimeLevelResult.class, levelId, (resultKey) -> levelResultDao.getResultsForLevel(TimeLevelResult.class, levelId));
				if (result != null) {
					singleResult.setWorlds(result.getResultValue());
					singleResult.setOwnerUserEmail(result.getUser().getEmail());
					if (!result.getUser().getEmail().equals(userEmail)) {
						result = levelResultDao.getResultsForLevel(TimeLevelResult.class, levelId, userEmail);
					}
					singleResult.setPersonal(result != null ? result.getResultValue() : null);
				}
				singleLevelResults.getFloatResults().put(LevelResultName.TIME, singleResult);
			}
			if (level.getResultNames().contains(LevelResultName.STEPS)) {
				LevelSingleResult<Integer> singleResult = new LevelSingleResult<Integer>();
				StepsLevelResult result = null; 
				result = levelResultCache.computeIfAbsent(StepsLevelResult.class, levelId, (resultKey) -> levelResultDao.getResultsForLevel(StepsLevelResult.class, levelId));
				if (result != null) {
					singleResult.setWorlds(result.getResultValue());
					singleResult.setOwnerUserEmail(result.getUser().getEmail());
					if (!result.getUser().getEmail().equals(userEmail)) {
						result = levelResultDao.getResultsForLevel(StepsLevelResult.class, levelId, userEmail);
					}
					singleResult.setPersonal(result != null ? result.getResultValue() : null);
				}
				singleLevelResults.getIntegerResults().put(LevelResultName.STEPS, singleResult);
			}
			if (level.getResultNames().contains(LevelResultName.LOST_UNITS)) {
				LevelSingleResult<Integer> singleResult = new LevelSingleResult<Integer>();
				LostUnitsLevelResult result = null; 
				result = levelResultCache.computeIfAbsent(LostUnitsLevelResult.class, levelId, (resultKey) -> levelResultDao.getResultsForLevel(LostUnitsLevelResult.class, levelId));
				if (result != null) {
					singleResult.setWorlds(result.getResultValue());
					singleResult.setOwnerUserEmail(result.getUser().getEmail());
					if (!result.getUser().getEmail().equals(userEmail)) {
						result = levelResultDao.getResultsForLevel(LostUnitsLevelResult.class, levelId, userEmail);
					}
					singleResult.setPersonal(result != null ? result.getResultValue() : null);
				}
				singleLevelResults.getIntegerResults().put(LevelResultName.LOST_UNITS, singleResult);
			}
			allLevelResults.getLevelResults().put(level.getLevelId(), singleLevelResults);
		});

		return allLevelResults;
	}
	
	private static AllLevelResults createTestLevelResults(String email) {
		AllLevelResults allLevelResults = new AllLevelResults();
		allLevelResults.setUserEmail(email);
		
		LevelResults singleLevelResults = null;
		
		singleLevelResults = new LevelResults();
		singleLevelResults.getFloatResults().put(LevelResultName.TIME, 
				new LevelSingleResult<Float>(100f, 45f, "world.@mail.com"));
		singleLevelResults.getIntegerResults().put(LevelResultName.STEPS, 
				new LevelSingleResult<Integer>(65, 32, "world.@mail.com"));
		singleLevelResults.getIntegerResults().put(LevelResultName.LOST_UNITS, 
				new LevelSingleResult<Integer>(16, 11, "world.@mail.com"));
		allLevelResults.getLevelResults().put("ozo.level.0", singleLevelResults);
		
		singleLevelResults = new LevelResults();
		singleLevelResults.getFloatResults().put(LevelResultName.TIME, 
				new LevelSingleResult<Float>(45f, 36f, "world.@mail.com"));
		singleLevelResults.getIntegerResults().put(LevelResultName.STEPS, 
				new LevelSingleResult<Integer>(55, 25, "world.@mail.com"));
		singleLevelResults.getIntegerResults().put(LevelResultName.LOST_UNITS, 
				new LevelSingleResult<Integer>(12, 6, "world.@mail.com"));
		allLevelResults.getLevelResults().put("ozo.level.1", singleLevelResults);
	
		return allLevelResults;
	}
}

/*

{ 
	"results": {
		"userEmail": "max@gmail.com",
		"levelResults": [
			{
				"level": {
					"levelId": "ozo.level.0",
					"results": [
						{ "TIME": { "personal": "66.23", "world": "55.55", "worldUser": "record@gmail.com" }},
						{ "STEPS": { "personal": "54", "world": "36", "worldUser": "record@gmail.com" }},
						{ "LOST_UNITS": { "personal": "14", "world": "9", "worldUser": "record@gmail.com" }}
					]
				}
			},
			{
				"level": {
					"levelId": "ozo.level.1",
					"results": [
						{ "TIME": { "personal": "112.23", "world": "85.55", "worldUser": "record@gmail.com" }},
						{ "STEPS": { "personal": "110", "world": "88", "worldUser": "record@gmail.com" }},
						{ "LOST_UNITS": { "personal": "64", "world": "15", "worldUser": "record@gmail.com" }}
					]
				}
			}
		]
	}
}









*/

