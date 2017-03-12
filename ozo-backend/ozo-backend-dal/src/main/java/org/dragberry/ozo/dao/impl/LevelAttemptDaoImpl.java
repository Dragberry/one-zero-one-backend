package org.dragberry.ozo.dao.impl;

import org.dragberry.ozo.dao.LevelAttemptDao;
import org.dragberry.ozo.domain.LevelAttempt;
import org.springframework.stereotype.Repository;

@Repository
public class LevelAttemptDaoImpl extends AbstractDao<LevelAttempt, Long> implements LevelAttemptDao{

	public LevelAttemptDaoImpl() {
		super(LevelAttempt.class);
	}
	
}
