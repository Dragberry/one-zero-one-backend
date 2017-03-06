package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class NewLevelResultsResponse implements Serializable {

	private static final long serialVersionUID = 4846478180576077174L;
	
	private String userId;
	
	private String levelId;
	
	private Map<LevelResultName, NewLevelResultResponse<Integer>> results = new HashMap<LevelResultName, NewLevelResultResponse<Integer>>();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(MessageFormat.format("ResultsResponse: userId=[{0}]; leveId=[{1}]\n", userId, levelId));
		for (NewLevelResultResponse<Integer> result : results.values()) {
			sb.append("\t").append(result).append("\n");
		}
		return sb.toString();
	}

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
