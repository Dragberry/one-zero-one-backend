package org.dragberry.ozo.dao;

import java.io.Serializable;
import java.util.List;

import org.dragberry.ozo.domain.DomainEntity;

/**
 * 
 * @author Maksim Drahun
 *
 */
public interface DataAccessObject<E extends DomainEntity, ID extends Serializable> {

	E findOne(ID entityKey);
	
	List<E> fetchList();
	
	Long count();
	
	E create(E entity);
	
	E update(E entity);
	
	E delete(ID entityKey);
	
}
