package org.dragberry.ozo.service;

import java.util.Map;
import java.util.function.Function;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelResultCacheServiceBean implements LevelResultCacheService {
	
	@Autowired
	private Map<ResultKey, LevelResult<Integer>> cache;
	
	@Override
	public LevelResult<Integer> getResultsForLevel(LevelResultName name, LevelId levelId) {
		return cache.get(new ResultKey(name, levelId));
	}
	
	@Override
	public void putResultsForLevel(LevelResultName name, LevelId levelId, LevelResult<Integer> levelResult) {
		cache.put(new ResultKey(name, levelId), levelResult);
	}
	
	@Override
	public LevelResult<Integer> computeIfAbsent(LevelResultName name, LevelId levelId, Function<? super ResultKey, ? extends LevelResult<Integer>> mappingFunction) {
		return cache.computeIfAbsent(new ResultKey(name, levelId), mappingFunction);
	}
	
	public static class ResultKey {
		private final LevelResultName name;
		private final LevelId levelId;
		
		public ResultKey(LevelResultName name, LevelId levelId) {
			this.name = name;
			this.levelId = levelId;
		}

		@Override
		public int hashCode() {
			return 31 + 31 * name.hashCode() + 31 * levelId.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || !(obj instanceof ResultKey)) {
				return false;
			}
			ResultKey key = (ResultKey) obj;
			return name == key.name && levelId.equals(key.levelId);
		}
	}
}
