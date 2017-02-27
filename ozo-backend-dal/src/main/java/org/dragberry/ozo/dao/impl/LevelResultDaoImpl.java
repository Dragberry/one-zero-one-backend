package org.dragberry.ozo.dao.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.LevelId;
import org.dragberry.ozo.domain.LevelResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LevelResultDaoImpl implements LevelResultDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public <T extends LevelResult<?>> T getResultsForLevel(Class<T> resultClass, LevelId levelId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(resultClass);
		Root<T> root = cq.from(resultClass);
		
		Subquery<Number> sq = cq.subquery(Number.class);
		Root<T> sqRoot = sq.from(resultClass);
		sq.select(cb.min(sqRoot.get("resultValue")))
			.where(cb.equal(sqRoot.get("level").get("entityKey"), levelId));
		
		cq.select(root).where(cb.and(
				cb.equal(root.get("level").get("entityKey"), levelId),
				cb.equal(root.get("resultValue"), sq.getSelection())));
		
		List<T> list = entityManager.createQuery(cq).getResultList();
		return list.size() == 1 ? list.get(0) : null;
	}
	

	@Override
	public <T extends LevelResult<?>> T getResultsForLevel(Class<T> resultClass, LevelId levelId, String userEmail) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(resultClass);
		Root<T> root = cq.from(resultClass);
		
		Subquery<Number> sq = cq.subquery(Number.class);
		Root<T> sqRoot = sq.from(resultClass);
		sq.select(cb.min(sqRoot.get("resultValue")))
			.where(cb.equal(sqRoot.get("level").get("entityKey"), levelId));
		
		cq.select(root).where(cb.and(
				cb.equal(root.get("level").get("entityKey"), levelId),
				cb.equal(root.get("user").get("email"), userEmail),
				cb.equal(root.get("resultValue"), sq.getSelection())));
		
		List<T> list = entityManager.createQuery(cq).getResultList();
		return list.size() == 1 ? list.get(0) : null;
	}
	
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
	public LevelResult<?> create(LevelResult<? extends Serializable> entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public LevelResult<?> update(LevelResult<? extends Serializable> entity) {
		return entityManager.merge(entity);
	}

	@Override
	public LevelResult<?> delete(Long entityKey) {
		LevelResult<?> entity = entityManager.find(LevelResult.class, entityKey);
		entityManager.remove(entity);
		return entity;
	}


}
