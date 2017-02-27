package org.dragberry.ozo.web.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dragberry.ozo.common.level.Levels;
import org.dragberry.ozo.common.levelresult.AllLevelResults;
import org.dragberry.ozo.common.levelresult.LevelSingleResult;
import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.domain.LostUnitsLevelResult;
import org.dragberry.ozo.domain.StepsLevelResult;
import org.dragberry.ozo.domain.TimeLevelResult;
import org.dragberry.ozo.service.LevelResultCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
				new SingleResultBuiler<Float>(singleLevelResults, TimeLevelResult.class) {

					@Override
					Map<LevelResultName,LevelSingleResult<Float>> getResultList() {
						return singleLevelResults.getFloatResults();
					}
					
				}.build(userEmail, levelId, LevelResultName.TIME);
			}
			if (level.getResultNames().contains(LevelResultName.STEPS)) {
				new SingleResultBuiler<Integer>(singleLevelResults, StepsLevelResult.class) {

					@Override
					Map<LevelResultName,LevelSingleResult<Integer>> getResultList() {
						return singleLevelResults.getIntegerResults();
					}
					
				}.build(userEmail, levelId, LevelResultName.STEPS);
			}
			if (level.getResultNames().contains(LevelResultName.LOST_UNITS)) {
				new SingleResultBuiler<Integer>(singleLevelResults, LostUnitsLevelResult.class) {

					@Override
					Map<LevelResultName,LevelSingleResult<Integer>> getResultList() {
						return singleLevelResults.getIntegerResults();
					}
					
				}.build(userEmail, levelId, LevelResultName.LOST_UNITS);
			}
			allLevelResults.getLevelResults().put(level.getLevelId(), singleLevelResults);
		});

		return allLevelResults;
	}
	
	private abstract class SingleResultBuiler<V extends Serializable> {
		
		protected final LevelResults singleLevelResults;
		protected final Class<? extends LevelResult<V>> clazz;
		
		SingleResultBuiler(LevelResults singleLevelResults, Class<? extends LevelResult<V>> clazz) {
			this.singleLevelResults = singleLevelResults;
			this.clazz = clazz;
		}
		
		void build(String userEmail, LevelId levelId, LevelResultName resultName) {
			LevelSingleResult<V> singleResult = new LevelSingleResult<V>();
			LevelResult<V> result = null; 
			result = levelResultCache.computeIfAbsent(clazz, levelId, (resultKey) -> levelResultDao.getResultsForLevel(clazz, levelId));
			if (result != null) {
				singleResult.setWorlds(result.getResultValue());
				singleResult.setOwnerUserEmail(result.getUser().getEmail());
				if (!result.getUser().getEmail().equals(userEmail)) {
					result = levelResultDao.getResultsForLevel(clazz, levelId, userEmail);
				}
				singleResult.setPersonal(result != null ? result.getResultValue() : null);
			}
			getResultList().put(resultName, singleResult);
		}
		
		abstract Map<LevelResultName,LevelSingleResult<V>> getResultList();
	}

}