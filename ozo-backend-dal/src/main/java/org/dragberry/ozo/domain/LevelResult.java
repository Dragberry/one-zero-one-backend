package org.dragberry.ozo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

@MappedSuperclass
public abstract class LevelResult<T extends Serializable> implements DomainEntity {

	private static final long serialVersionUID = -5036495150092169600L;
	
	@Id
	@Column(name = "LEVEL_RESULT_KEY")
	@TableGenerator(
			name = "LEVEL_RESULT_GEN", 
			table = "GENERATOR",
			pkColumnName = "GEN_NAME", 
			pkColumnValue = "LEVEL_RESULT_GEN",
			valueColumnName = "GEN_VALUE",
			initialValue = 1000,
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LEVEL_RESULT_GEN")
	private Long entityKey;
	
	@Column(name = "VALUE")
	private T resultValue;
	
	@Column(name = "DATE")
	private LocalDateTime date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_KEY", referencedColumnName = "USER_KEY")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEVEL_ID", referencedColumnName = "LEVEL_ID")
	private Level level;
	
	@Override
	public Long getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(Long entityKey) {
		this.entityKey = entityKey;
	}

	public T getResultValue() {
		return resultValue;
	}

	public void setResultValue(T resultValue) {
		this.resultValue = resultValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
