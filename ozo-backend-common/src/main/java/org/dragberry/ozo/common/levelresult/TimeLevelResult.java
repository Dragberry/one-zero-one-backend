package org.dragberry.ozo.common.levelresult;

public class TimeLevelResult extends LevelResult<Float> {

	@Override
	public boolean isRecordBeaten(Float newValue) {
		return value > newValue;
	}

}
