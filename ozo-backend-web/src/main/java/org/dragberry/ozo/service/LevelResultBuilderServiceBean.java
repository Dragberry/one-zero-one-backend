package org.dragberry.ozo.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.common.levelresult.LevelResults;
import org.dragberry.ozo.common.levelresult.LevelSingleResult;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.dragberry.ozo.domain.LostUnitsLevelResult;
import org.dragberry.ozo.domain.StepsLevelResult;
import org.dragberry.ozo.domain.TimeLevelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelResultBuilderServiceBean implements LevelResultBuilderService {
	
	private abstract class SingleResultBuiler<V extends Serializable> {
		
		protected final LevelResultName resultName;
		protected final Class<? extends LevelResult<V>> clazz;
		
		SingleResultBuiler(LevelResultName resultName, Class<? extends LevelResult<V>> clazz) {
			this.resultName = resultName;
			this.clazz = clazz;
		}
		
		void build(String userId, LevelId levelId, LevelResults singleLevelResults) {
			LevelSingleResult<V> singleResult = new LevelSingleResult<V>();
			LevelResult<V> result = null; 
			result = levelResultCache.computeIfAbsent(clazz, levelId, (resultKey) -> levelResultDao.getResultsForLevel(clazz, levelId));
			if (result != null) {
				singleResult.setWorlds(result.getResultValue());
				singleResult.setOwner(result.getUser().getUserName());
				if (!result.getUser().getUserId().equals(userId)) {
					result = levelResultDao.getResultsForLevel(clazz, levelId, userId);
				}
				singleResult.setPersonal(result != null ? result.getResultValue() : null);
			}
			getResultList(singleLevelResults).put(resultName, singleResult);
		}
		
		abstract Map<LevelResultName,LevelSingleResult<V>> getResultList(LevelResults singleLevelResults);
	}
	
	@Autowired
	private LevelResultDao levelResultDao;
	
	@Autowired
	private LevelResultCacheService levelResultCache;
	
	private final Map<LevelResultName, SingleResultBuiler<?>> resultBuilders = new HashMap<>();
	
	public LevelResultBuilderServiceBean() {
		resultBuilders.put(LevelResultName.TIME, new SingleResultBuiler<Float>(LevelResultName.TIME, TimeLevelResult.class) {

			@Override
			Map<LevelResultName,LevelSingleResult<Float>> getResultList(LevelResults singleLevelResults) {
				return singleLevelResults.getFloatResults();
			}
			
		});
		resultBuilders.put(LevelResultName.STEPS, new SingleResultBuiler<Integer>(LevelResultName.STEPS, StepsLevelResult.class) {

			@Override
			Map<LevelResultName,LevelSingleResult<Integer>> getResultList(LevelResults singleLevelResults) {
				return singleLevelResults.getIntegerResults();
			}
			
		});
		resultBuilders.put(LevelResultName.LOST_UNITS, new SingleResultBuiler<Integer>(LevelResultName.LOST_UNITS, LostUnitsLevelResult.class) {

			@Override
			Map<LevelResultName,LevelSingleResult<Integer>> getResultList(LevelResults singleLevelResults) {
				return singleLevelResults.getIntegerResults();
			}
			
		});
	}
	
	@Override
	public void build(LevelResultName resultName, String userId, LevelId levelId, LevelResults singleLevelResults) {
		resultBuilders.get(resultName).build(userId, levelId, singleLevelResults);
	}
	
}
