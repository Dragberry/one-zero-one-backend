package org.dragberry.ozo.common.levelresult;

import java.io.Serializable;
import java.text.MessageFormat;

public class NewLevelResultRequest<V extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1771865981934548201L;
	
	public NewLevelResultRequest(V value) {
		this.value = value;
	}
	
	public NewLevelResultRequest() {}
	
	@Override
	public String toString() {
		return MessageFormat.format("value={0}", value);
	}
	
	private V value;

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
}
