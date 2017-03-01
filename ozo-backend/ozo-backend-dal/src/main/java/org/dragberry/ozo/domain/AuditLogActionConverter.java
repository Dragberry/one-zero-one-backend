package org.dragberry.ozo.domain;

import javax.persistence.AttributeConverter;

public class AuditLogActionConverter implements AttributeConverter<AuditLogAction, Character> {

	@Override
	public Character convertToDatabaseColumn(AuditLogAction attribute) {
		return attribute.getValue();
	}

	@Override
	public AuditLogAction convertToEntityAttribute(Character dbData) {
		return AuditLogAction.valueOf(dbData);
	}

}
