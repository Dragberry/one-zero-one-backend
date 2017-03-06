package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NewLevelResultsRequest implements Serializable {

	private static final long serialVersionUID = 4846478180576077174L;
	
	private String userId;
	
	private String levelId;
	
	private Map<LevelResultName, NewLevelResultRequest<Integer>> results = new HashMap<LevelResultName, NewLevelResultRequest<Integer>>();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(MessageFormat.format("ResultsRequest: userId=[{0}]; levelId=[{1}]\n", userId, levelId));
		for (Entry<LevelResultName, NewLevelResultRequest<Integer>> result : results.entrySet()) {
			sb.append("\t").append(result.getKey()).append(": ").append(result.getValue()).append("\n");
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

	public Map<LevelResultName, NewLevelResultRequest<Integer>> getResults() {
		return results;
	}

	public void setResults(Map<LevelResultName, NewLevelResultRequest<Integer>> results) {
		this.results = results;
	}

}
