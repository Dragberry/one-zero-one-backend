package org.dragberry.ozo.dao;

import java.io.Serializable;

import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;

public interface LevelResultDao extends DataAccessObject<LevelResult<? extends Serializable>, Long> {

	<T extends LevelResult<?>> T getResultsForLevel(Class<T> resultClass, LevelId levelId);

	<T extends LevelResult<?>> T getResultsForLevel(Class<T> resultClass, LevelId levelId, String userId);
}
