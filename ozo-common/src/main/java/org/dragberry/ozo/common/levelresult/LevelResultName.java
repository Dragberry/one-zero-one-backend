package org.dragberry.ozo.common.levelresult;

public enum LevelResultName {
	
	TIME, STEPS, LOST_UNITS;
	
	public String personal() {
		return name() + ".result.personal";
	}
	
	public String worlds() {
		return name() + ".result.worlds";
	}
	
	public String owner() {
		return name() + ".result.owner";
	}

}
