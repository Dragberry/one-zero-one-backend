package org.dragberry.ozo.dao.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.dragberry.ozo.dao.DataAccessObject;
import org.dragberry.ozo.domain.DomainEntity;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDao<E extends DomainEntity, ID extends Serializable> implements DataAccessObject<E, ID> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Class<E> entityType;
	
	public AbstractDao(Class<E> entityType) {
		this.entityType = entityType;
	}
	
	@Override
	public E findOne(ID entityKey) {
		return entityManager.find(entityType, entityKey);
	}
	
	@Override
	public List<E> fetchList() {
		return getEntityManager().createQuery("FROM " + getEntityName(), entityType).getResultList();
	}
	
	@Override
	public Long count() {
		return getEntityManager().createQuery("SELECT COUNT(e) FROM " + getEntityName() + " e", Long.class).getSingleResult();
	}
	
	@Override
	@Transactional
	public E create(E entity) {
		entityManager.persist(entity);
		return entity; 
	}
	
	@Override
	@Transactional
	public E update(E entity) {
		return entityManager.merge(entity);
	}
	
	@Override
	@Transactional
	public E delete(ID entityKey) {
		E entity = entityManager.find(entityType, entityKey);
		entityManager.remove(entity);
		return entity;
	}
	
	protected String getEntityName() {
		return entityType.getName();
	}
	
	protected Class<E> getEntityType() {
		return entityType;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
}
