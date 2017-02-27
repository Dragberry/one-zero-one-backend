package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;

public abstract class LevelResult<V extends Serializable> {
	
	protected V value;
	
	public abstract boolean isRecordBeaten(V newValue);

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
}