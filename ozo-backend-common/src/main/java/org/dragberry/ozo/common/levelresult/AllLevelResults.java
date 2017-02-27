package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllLevelResults implements Serializable {

	private static final long serialVersionUID = 4440334600460572470L;
	
	private String userEmail;
	
	private Map<String, LevelResults> levelResults = new HashMap<>();

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Map<String, LevelResults> getLevelResults() {
		return levelResults;
	}

	public void setLevelResults(Map<String, LevelResults> levelResults) {
		this.levelResults = levelResults;
	}

}
