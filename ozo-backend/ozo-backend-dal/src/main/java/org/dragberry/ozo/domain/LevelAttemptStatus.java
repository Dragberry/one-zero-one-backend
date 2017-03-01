package org.dragberry.ozo.domain;

import java.text.MessageFormat;

public enum LevelAttemptStatus implements DomainEnum<Character> {

	SUCCESS('S'), FAILED('F'), INTERRUPTED('I');
	
	private char value;
	
	private LevelAttemptStatus(char value) {
		this.value = value;
	}
	
	public Character getValue() {
		return value;
	}

	public static LevelAttemptStatus valueOf(Character value) {
		if (value == null) {
			throw new NullPointerException(NPE_MSG);
		}
		for (LevelAttemptStatus status : LevelAttemptStatus.values()) {
			if (value.equals(status.getValue())) {
				return status;
			}
		}
		throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_VALUE_MSG, value));
	}
}
