package org.dragberry.ozo.dao.impl;

import org.dragberry.ozo.dao.LevelDao;
import org.dragberry.ozo.domain.Level;
import org.dragberry.ozo.domain.LevelId;
import org.springframework.stereotype.Repository;

@Repository
public class LevelDaoImpl extends AbstractDao<Level, LevelId> implements LevelDao {

	public LevelDaoImpl() {
		super(Level.class);
	}
}
