package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LevelResults implements Serializable {

	private static final long serialVersionUID = -1830353672943199933L;

	private Map<LevelResultName, LevelSingleResult<Integer>> integerResults = new HashMap<>();

	private Map<LevelResultName, LevelSingleResult<Float>> floatResults = new HashMap<>();

	public Map<LevelResultName, LevelSingleResult<Integer>> getIntegerResults() {
		return integerResults;
	}

	public void setIntegerResults(Map<LevelResultName, LevelSingleResult<Integer>> integerResults) {
		this.integerResults = integerResults;
	}

	public Map<LevelResultName, LevelSingleResult<Float>> getFloatResults() {
		return floatResults;
	}

	public void setFloatResults(Map<LevelResultName, LevelSingleResult<Float>> floatResults) {
		this.floatResults = floatResults;
	}

}
