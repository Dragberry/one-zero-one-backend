package org.dragberry.ozo.common.levelresult;

import org.dragberry.ozo.common.util.TimeUtils;

public enum LevelResultName {
	
	TIME(new TimeStringifier(), new LessResultComparator()), 
	STEPS(new IntegerStringifier(), new LessResultComparator()),
	LOST_UNITS(new IntegerStringifier(), new LessResultComparator()),
	MAX_VALUE(new IntegerStringifier(), new GreaterResultComparator()),
	MAX_AND_LOST(new IntegerStringifier(), new GreaterResultComparator());
	
	private Stringifier<Integer> stringifier;
	private ResultComparator<Integer> comparator;
	
	private LevelResultName(Stringifier<Integer> stringifier, ResultComparator<Integer> comparator) {
		this.stringifier = stringifier;
		this.comparator = comparator;
	}
	
	public boolean isRecordBeaten(Integer old, Integer newValue) {
		return comparator.isRecordBeaten(old, newValue);
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
	
	private static interface ResultComparator<T> {
		
		boolean isRecordBeaten(T old, T newValue);
		
	}
	
	private static class GreaterResultComparator implements ResultComparator<Integer> {

		@Override
		public boolean isRecordBeaten(Integer old, Integer newValue) {
			return newValue > old;
		}
		
	}
	
	private static class LessResultComparator implements ResultComparator<Integer> {

		@Override
		public boolean isRecordBeaten(Integer old, Integer newValue) {
			return newValue < old;
		}
		
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
