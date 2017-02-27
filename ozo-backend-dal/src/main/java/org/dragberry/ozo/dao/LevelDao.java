package org.dragberry.ozo.dao;

import java.util.List;

import org.dragberry.ozo.domain.Level;
import org.dragberry.ozo.domain.LevelId;

public interface LevelDao extends DataAccessObject<Level, LevelId> {

	List<Level> fetchList(List<LevelId> levelIds);

}
