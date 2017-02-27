package org.dragberry.ozo.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL")
public class Level implements DomainEntity {

	private static final long serialVersionUID = -2859662887286754833L;
	
	@EmbeddedId
	private LevelId entityKey;
	
	@Override
	public LevelId getEntityKey() {
		return entityKey;
	}
	
	public void setEntityKey(LevelId entityKey) {
		this.entityKey = entityKey;
	}
	

}
