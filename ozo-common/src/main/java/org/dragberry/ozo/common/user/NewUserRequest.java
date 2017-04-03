package org.dragberry.ozo.common.user;

import java.io.Serializable;

public class NewUserRequest implements Serializable {

	private static final long serialVersionUID = -8743994612541137962L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "UserRequest:[userName=" + userName +"]";
	}
	
}
