package org.dragberry.ozo.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.dragberry.ozo.common.audit.LevelAttemptStatus;

@Entity
@Table(name = "LEVEL_ATTEMPT")
@TableGenerator(
		name = "LEVEL_ATTEMPT_GEN", 
		table = "GENERATOR",
		pkColumnName = "GEN_NAME", 
		pkColumnValue = "LEVEL_ATTEMPT_GEN",
		valueColumnName = "GEN_VALUE",
		initialValue = 1000,
		allocationSize = 1)
public class LevelAttempt implements DomainEntity {

	private static final long serialVersionUID = 8054807799264148522L;
	
	@Id
	@Column(name = "LEVEL_ATTEMPT_KEY")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LEVEL_ATTEMPT_GEN")
	private Long entityKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEVEL_ID", referencedColumnName = "LEVEL_ID")
	private Level level;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUDIT_EVENT_KEY", referencedColumnName = "AUDIT_EVENT_KEY")
	private AuditEvent auditEvent;
	
	@Column(name = "STATUS")
	@Convert(converter = LevelAttemptStatusConverter.class)
	private LevelAttemptStatus status;
	
	@Column(name = "TIME")
	private Integer time;

	@Column(name = "STEPS")
	private Integer steps;
	
	@Column(name = "LOST_UNITS")
	private Integer lostUnits;
	
	@Override
	public Long getEntityKey() {
		return entityKey;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public AuditEvent getAuditEvent() {
		return auditEvent;
	}

	public void setAuditEvent(AuditEvent auditEvent) {
		this.auditEvent = auditEvent;
	}

	public LevelAttemptStatus getStatus() {
		return status;
	}

	public void setStatus(LevelAttemptStatus status) {
		this.status = status;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Integer getLostUnits() {
		return lostUnits;
	}

	public void setLostUnits(Integer lostUnits) {
		this.lostUnits = lostUnits;
	}

	public void setEntityKey(Long entityKey) {
		this.entityKey = entityKey;
	}

}
