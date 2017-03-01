package org.dragberry.ozo.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LEVEL")
@NamedQuery(
		name = Level.FETCH_LIST_BY_IDS,
		query = "SELECT l FROM Level l WHERE l.entityKey IN (:levelIds)"
)
public class Level implements DomainEntity {

	private static final long serialVersionUID = -2859662887286754833L;
	
	public final static String FETCH_LIST_BY_IDS = "Level.FetchListByIds";
	
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
