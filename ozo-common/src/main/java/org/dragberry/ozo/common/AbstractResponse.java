package org.dragberry.ozo.common;

import java.io.Serializable;

public class AbstractResponse implements Serializable {

	private static final long serialVersionUID = -7599859542850681001L;

	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
