package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NewLevelResultsResponse implements Serializable {

	private static final long serialVersionUID = 4846478180576077174L;
	
	private String userId;
	
	private String levelId;
	
	private Map<LevelResultName, NewLevelResultResponse<Integer>> results = new HashMap<>();

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

	public Map<LevelResultName, NewLevelResultResponse<Integer>> getResults() {
		return results;
	}

	public void setResults(Map<LevelResultName, NewLevelResultResponse<Integer>> results) {
		this.results = results;
	}

}
