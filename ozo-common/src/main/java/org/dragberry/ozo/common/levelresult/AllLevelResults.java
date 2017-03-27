package org.dragberry.ozo.common.levelresult;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.dragberry.ozo.common.AbstractResponse;

public class AllLevelResults extends AbstractResponse {

	private static final long serialVersionUID = 4440334600460572470L;
	
	private String userName;
	
	private Map<String, LevelResults> levelResults = new HashMap<String, LevelResults>();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(MessageFormat.format("AllLevelResults: userName=[{0}]", userName));
		for (LevelResults results : levelResults.values()) {
			sb.append(results).append("\n");
		}
		return sb.toString();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, LevelResults> getLevelResults() {
		return levelResults;
	}

	public void setLevelResults(Map<String, LevelResults> levelResults) {
		this.levelResults = levelResults;
	}

}
