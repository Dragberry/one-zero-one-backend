package org.dragberry.ozo.domain;

import java.time.LocalTime;

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

@Entity
@Table(name = "AUDIT_LOG")
@TableGenerator(
		name = "AUDIT_LOG_GEN", 
		table = "GENERATOR",
		pkColumnName = "GEN_NAME", 
		pkColumnValue = "AUDIT_LOG_GEN",
		valueColumnName = "GEN_VALUE",
		initialValue = 1000,
		allocationSize = 1)
public class AuditLog implements DomainEntity {

	private static final long serialVersionUID = 6577579709058166467L;

	@Id
	@Column(name = "AUDIT_LOG_KEY")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AUDIT_LOG_GEN")
	private Long entityKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_KEY", referencedColumnName = "USER_KEY")
	private User user;
	
	@Column(name = "ACTION")
	@Convert(converter = AuditLogActionConverter.class)
	private AuditLogAction action;
	

	@Column(name = "DATE")
	private LocalTime date;
	
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

	public AuditLogAction getAction() {
		return action;
	}

	public void setAction(AuditLogAction action) {
		this.action = action;
	}

	public LocalTime getDate() {
		return date;
	}

	public void setDate(LocalTime date) {
		this.date = date;
	}

	public void setEntityKey(Long entityKey) {
		this.entityKey = entityKey;
	}

}
