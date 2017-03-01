package org.dragberry.ozo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.dragberry.ozo.common.levelresult.LevelResultName;
import org.dragberry.ozo.dao.LevelResultDao;
import org.dragberry.ozo.domain.IntegerLevelResult;
import org.dragberry.ozo.domain.LevelId;
import org.springframework.stereotype.Repository;

@Repository
public class LevelResultDaoImpl extends AbstractDao<IntegerLevelResult, Long> implements LevelResultDao {

	public LevelResultDaoImpl() {
		super(IntegerLevelResult.class);
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public IntegerLevelResult getLevelResult(LevelId levelId, LevelResultName name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<IntegerLevelResult> cq = cb.createQuery(entityType);
		Root<IntegerLevelResult> root = cq.from(entityType);
		
		Subquery<Integer> sq = cq.subquery(Integer.class);
		Root<IntegerLevelResult> sqRoot = sq.from(entityType);
		sq.select(cb.min(sqRoot.get("resultValue")))
			.where(cb.and(
					cb.equal(sqRoot.get("level").get("entityKey"), levelId)),
					cb.equal(sqRoot.get("name"), name));
		
		cq.select(root).where(cb.and(
				cb.equal(root.get("level").get("entityKey"), levelId),
				cb.equal(root.get("resultValue"), sq.getSelection())),
				cb.equal(root.get("name"), name));
		
		cq.orderBy(cb.asc(root.get("date")));
		
		List<IntegerLevelResult> list = entityManager.createQuery(cq).getResultList();
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	public IntegerLevelResult getLevelResultForUser(LevelId levelId, LevelResultName name, String userId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<IntegerLevelResult> cq = cb.createQuery(entityType);
		Root<IntegerLevelResult> root = cq.from(entityType);
		
		cq.select(root).where(cb.and(
				cb.equal(root.get("user").get("userId"), userId)),
				cb.equal(root.get("level").get("entityKey"), levelId),
				cb.equal(root.get("name"), name));
		
		
		List<IntegerLevelResult> list = entityManager.createQuery(cq).getResultList();
		return list.size() == 1 ? list.get(0) : null;
	}
	
}
