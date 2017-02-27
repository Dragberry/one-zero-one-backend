package org.dragberry.ozo.common.levelresult;

public class StepsRecord extends LevelResult<Integer> {

	@Override
	public boolean isRecordBeaten(Integer newValue) {
		return value > newValue;
	}

}
