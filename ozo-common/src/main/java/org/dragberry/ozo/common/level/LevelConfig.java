package org.dragberry.ozo.common.level;

import java.util.EnumSet;

import org.dragberry.ozo.common.levelresult.LevelResultName;

public class LevelConfig {
	
	private String levelId;
	
	private EnumSet<LevelResultName> resultNames;
	
	public LevelConfig(String levelId, EnumSet<LevelResultName> resultNames) {
		super();
		this.levelId = levelId;
		this.resultNames = resultNames;
	}

	public String getLevelId() {
		return levelId;
	}

	public EnumSet<LevelResultName> getResultNames() {
		return resultNames;
	}

}
