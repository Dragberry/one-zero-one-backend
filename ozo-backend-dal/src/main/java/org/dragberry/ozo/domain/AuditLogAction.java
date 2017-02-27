package org.dragberry.ozo.domain;

import java.text.MessageFormat;

public enum AuditLogAction implements DomainEnum<Character> {
	
	LAUNCH_APPLICATION('L'), EXIT_APPLICATION('E'), START_LEVEL('S');
	
	private char value;
	
	private AuditLogAction(char value) {
		this.value = value;
	}
	
	@Override
	public Character getValue() {
		return value;
	}
	
	public static AuditLogAction valueOf(Character value) {
		if (value == null) {
			throw new NullPointerException(NPE_MSG);
		}
		for (AuditLogAction action : AuditLogAction.values()) {
			if (value.equals(action.getValue())) {
				return action;
			}
		}
		throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_VALUE_MSG, value));
	}
}
