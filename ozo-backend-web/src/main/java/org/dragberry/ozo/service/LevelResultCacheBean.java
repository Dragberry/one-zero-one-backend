package org.dragberry.ozo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.springframework.stereotype.Service;

@Service
public class LevelResultCacheBean implements LevelResultCacheService {
	
	private Map<ResultKey, LevelResult<?>> cache = new ConcurrentHashMap<>();
	
	@SuppressWarnings("unchecked")
	public <T extends LevelResult<?>> T getResultsForLevel(Class<? extends LevelResult<?>> clazz, LevelId levelId) {
		return (T) cache.get(new ResultKey(clazz, levelId));
	}
	
	@Override
	public <T extends LevelResult<?>> void putResultsForLevel(Class<? extends LevelResult<?>> clazz, LevelId levelId, T levelResult) {
		cache.put(new ResultKey(clazz, levelId), levelResult);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends LevelResult<?>> T computeIfAbsent(Class<? extends LevelResult<?>> clazz, LevelId levelId, Function<? super ResultKey, ? extends LevelResult<?>> mappingFunction) {
		return (T) cache.computeIfAbsent(new ResultKey(clazz, levelId), mappingFunction);
	}
	
	public static class ResultKey {
		private Class<? extends LevelResult<?>> clazz;
		private LevelId levelId;
		
		public ResultKey(Class<? extends LevelResult<?>> clazz, LevelId levelId) {
			this.clazz = clazz;
			this.levelId = levelId;
		}

		@Override
		public int hashCode() {
			return 31 + 31 * clazz.hashCode() + 31 * levelId.hashCode();
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
			return clazz.equals(key.clazz) && levelId.equals(key.levelId);
		}
	}
}
