package org.dragberry.ozo.domain;

import javax.persistence.AttributeConverter;

public class LevelAttemptStatusConverter implements AttributeConverter<LevelAttemptStatus, Character> {

	@Override
	public Character convertToDatabaseColumn(LevelAttemptStatus attribute) {
		return attribute.getValue();
	}

	@Override
	public LevelAttemptStatus convertToEntityAttribute(Character dbData) {
		return LevelAttemptStatus.valueOf(dbData);
	}

}
