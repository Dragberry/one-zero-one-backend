package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NewLevelResultsRequest implements Serializable {

	private static final long serialVersionUID = 4846478180576077174L;
	
	private String userId;
	
	private String levelId;
	
	private Map<LevelResultName, NewLevelResult<Integer>> integerResults = new HashMap<>();

	private Map<LevelResultName, NewLevelResult<Float>> floatResults = new HashMap<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public Map<LevelResultName, NewLevelResult<Integer>> getIntegerResults() {
		return integerResults;
	}

	public void setIntegerResults(Map<LevelResultName, NewLevelResult<Integer>> integerResults) {
		this.integerResults = integerResults;
	}

	public Map<LevelResultName, NewLevelResult<Float>> getFloatResults() {
		return floatResults;
	}

	public void setFloatResults(Map<LevelResultName, NewLevelResult<Float>> floatResults) {
		this.floatResults = floatResults;
	}
	
}
