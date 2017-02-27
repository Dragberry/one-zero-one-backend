package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;

public class LevelBestResult implements Serializable {

	private static final long serialVersionUID = 3604974340318868123L;
	
	private String levelId;
	
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	
}
