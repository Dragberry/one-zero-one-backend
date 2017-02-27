package org.dragberry.ozo.common.levelresult;

public class LostUnitResult extends LevelResult<Integer> {

	@Override
	public boolean isRecordBeaten(Integer newValue) {
		return value > newValue;
	}

}
