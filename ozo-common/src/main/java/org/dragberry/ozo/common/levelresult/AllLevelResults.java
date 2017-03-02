package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllLevelResults implements Serializable {

	private static final long serialVersionUID = 4440334600460572470L;
	
	private String userName;
	
	private Map<String, LevelResults> levelResults = new HashMap<String, LevelResults>();

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
