package org.dragberry.ozo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.dragberry.ozo.common.audit.AuditEventType;

@Entity
@Table(name = "AUDIT_EVENT")
@TableGenerator(
		name = "AUDIT_EVENT_GEN", 
		table = "GENERATOR",
		pkColumnName = "GEN_NAME", 
		pkColumnValue = "AUDIT_EVENT_GEN",
		valueColumnName = "GEN_VALUE",
		initialValue = 1000,
		allocationSize = 1)
public class AuditEvent implements DomainEntity {

	private static final long serialVersionUID = 6577579709058166467L;

	@Id
	@Column(name = "AUDIT_EVENT_KEY")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AUDIT_EVENT_GEN")
	private Long entityKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_KEY", referencedColumnName = "USER_KEY")
	private User user;
	
	@Column(name = "ACTION")
	@Convert(converter = AuditEventTypeConverter.class)
	private AuditEventType type;
	

	@Column(name = "DATE")
	private LocalDateTime date;
	
	@Override
	public Long getEntityKey() {
		return entityKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AuditEventType getType() {
		return type;
	}

	public void setType(AuditEventType type) {
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setEntityKey(Long entityKey) {
		this.entityKey = entityKey;
	}


}
