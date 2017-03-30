package org.dragberry.ozo.common.audit;

import java.text.MessageFormat;

import org.dragberry.ozo.common.DomainEnum;

public enum AuditEventType implements DomainEnum<Character> {
	
	START_APPLICATION('S'), 
	EXIT_APPLICATION('E'),
	LEVEL_ATTEMPT('L');
	
	private char value;
	
	private AuditEventType(char value) {
		this.value = value;
	}
	
	@Override
	public Character getValue() {
		return value;
	}
	
	public static AuditEventType valueOf(Character value) {
		if (value == null) {
			throw new NullPointerException(NPE_MSG);
		}
		for (AuditEventType action : AuditEventType.values()) {
			if (value.equals(action.getValue())) {
				return action;
			}
		}
		throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_VALUE_MSG, value));
	}
}
