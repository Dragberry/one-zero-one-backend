package org.dragberry.ozo.common.levelresult;

import org.dragberry.ozo.common.level.TimeUtils;

public enum LevelResultName {
	
	TIME(new TimeBuilder()), STEPS(new StringBuilder()), LOST_UNITS(new StringBuilder());
	
	private StringBuilder stringBuilder;
	
	private LevelResultName(StringBuilder builder) {
		this.stringBuilder = builder;
	}
	
	public String key() {
		return "ozo.result." + name();
	}
	
	public String personal() {
		return name() + ".result.personal";
	}
	
	public String worlds() {
		return name() + ".result.worlds";
	}
	
	public String owner() {
		return name() + ".result.owner";
	}

	public String toString(Integer value) {
		return stringBuilder.build(value);
	}
	
	private static class StringBuilder {
		
		String build(Integer value) {
			return value.toString();
		}
	}
	
	private static class TimeBuilder extends StringBuilder {
		
		@Override
		String build(Integer value) {
			return TimeUtils.timeToString(value);
		}
	}
}
