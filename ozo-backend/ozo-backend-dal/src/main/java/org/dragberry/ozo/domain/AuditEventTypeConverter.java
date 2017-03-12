package org.dragberry.ozo.domain;

import javax.persistence.AttributeConverter;

import org.dragberry.ozo.common.audit.AuditEventType;

public class AuditEventTypeConverter implements AttributeConverter<AuditEventType, Character> {

	@Override
	public Character convertToDatabaseColumn(AuditEventType attribute) {
		return attribute.getValue();
	}

	@Override
	public AuditEventType convertToEntityAttribute(Character dbData) {
		return AuditEventType.valueOf(dbData);
	}

}
