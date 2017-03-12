package org.dragberry.ozo.dao.impl;

import org.dragberry.ozo.dao.AuditEventDao;
import org.dragberry.ozo.domain.AuditEvent;
import org.springframework.stereotype.Repository;

@Repository
public class AuditEventDaoImpl extends AbstractDao<AuditEvent, Long> implements AuditEventDao {

	public AuditEventDaoImpl() {
		super(AuditEvent.class);
	}
	
}
