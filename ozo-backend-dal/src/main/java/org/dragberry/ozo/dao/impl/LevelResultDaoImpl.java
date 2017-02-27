package org.dragberry.ozo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.LevelResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LevelResultDaoImpl implements LevelResultDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public LevelResult<?> findOne(Long entityKey) {
		return entityManager.find(LevelResult.class, entityKey);
	}

	@Override
	public List<LevelResult<?>> fetchList() {
		return null;
	}

	@Override
	public Long count() {
		return null;
	}

	@Override
	@Transactional
	public LevelResult<?> create(LevelResult<?> entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public LevelResult<?> update(LevelResult<?> entity) {
		return entityManager.merge(entity);
	}

	@Override
	public LevelResult<?> delete(Long entityKey) {
		LevelResult<?> entity = entityManager.find(LevelResult.class, entityKey);
		entityManager.remove(entity);
		return entity;
	}

}
