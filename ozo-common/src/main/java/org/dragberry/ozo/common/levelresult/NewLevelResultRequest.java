package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;

public class NewLevelResultRequest<V extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1771865981934548201L;
	
	public NewLevelResultRequest(V value) {
		this.value = value;
	}
	
	public NewLevelResultRequest() {}
	
	private V value;

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
}
