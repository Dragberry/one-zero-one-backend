package org.dragberry.ozo.common;

import java.io.Serializable;

public class AbstractResponse implements Serializable {

	private static final long serialVersionUID = -7599859542850681001L;

	private String version;
	
	public AbstractResponse() {
		version = CommonConstants.APP_VERSION;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
