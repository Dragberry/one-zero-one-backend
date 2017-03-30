package org.dragberry.ozo.common.levelresult;

import org.dragberry.ozo.common.util.TimeUtils;

public enum LevelResultName {
	
	TIME(new TimeStringifier()), 
	STEPS(new IntegerStringifier()),
	LOST_UNITS(new IntegerStringifier()),
	MAX_VALUE(new IntegerStringifier());
	
	private Stringifier<Integer> stringifier;
	
	private LevelResultName(Stringifier<Integer> stringifier) {
		this.stringifier = stringifier;
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
		return stringifier.stringify(value);
	}
	
	private static abstract class Stringifier<T> {
		
		String stringify(T value) {
			if (value == null) {
				return stringifyNull();
			}
			return stringifyNotNull(value);
		}
		
		abstract String stringifyNull();
		
		abstract String stringifyNotNull(T value);
	}
	
	private static class IntegerStringifier extends Stringifier<Integer> {

		@Override
		String stringifyNull() {
			return "-";
		}

		@Override
		String stringifyNotNull(Integer value) {
			return value.toString();
		}
		
		
	}
	
	private static class TimeStringifier extends Stringifier<Integer> {
		
		@Override
		String stringifyNull() {
			return "--:--";
		}

		@Override
		String stringifyNotNull(Integer value) {
			return TimeUtils.timeToString(value);
		}
		
	}
}
