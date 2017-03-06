package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LevelResults implements Serializable {

	private static final long serialVersionUID = -1830353672943199933L;

	private Map<LevelResultName, LevelSingleResult<Integer>> results = new HashMap<LevelResultName, LevelSingleResult<Integer>>();

	public Map<LevelResultName, LevelSingleResult<Integer>> getResults() {
		return results;
	}

	public void setResults(Map<LevelResultName, LevelSingleResult<Integer>> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("LevelResults:\n");
		for (LevelSingleResult<Integer> result : results.values()) {
			sb.append("\t").append(result).append("\n");
		}
		return sb.toString();
	}

}
