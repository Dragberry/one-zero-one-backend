package org.dragberry.ozo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LevelId implements Serializable {

	private static final long serialVersionUID = 2509227126443070231L;
	
	private static int HASH_FACTOR = 31;

	@Column(name = "LEVEL_ID")
	private String levelId;
	
	public LevelId() {}
	
	public LevelId(String levelId) {
		this.levelId = levelId;
	}
	
	public String getLevelId() {
		return levelId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof LevelId)) {
			return false;
		}
		if (levelId != null && ((LevelId) obj).levelId != null && levelId.equals(((LevelId) obj).levelId)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return levelId == null ? 0 : HASH_FACTOR + levelId.hashCode();
	}
}
