package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;

public class NewLevelResult<V extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1771865981934548201L;
	
	public NewLevelResult(V value) {
		this.value = value;
	}
	
	public NewLevelResult() {}
	
	private V value;

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
}
